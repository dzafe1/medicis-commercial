package com.medicis.commercial.controllers;

import com.medicis.commercial.domain.User;
import com.medicis.commercial.repository.UserRepository;
import com.medicis.commercial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController  {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true, 10));
    }

    @GetMapping(value = "/login-register")
    public String loginRegister(Model model){
        model.addAttribute("user",new User());
        return "login-register";
    }
   /* @GetMapping(value = "/test")
    public void sms(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(

                new PhoneNumber("+38761540023"),
                new PhoneNumber("+14159681251"),// From number
                "Sto se ne javi nikad"                    // SMS body
        ).create();
    }*/

    @PostMapping(value = "/register")
    public String register(@Valid User user, BindingResult bindingResult,
                           @RequestParam("fName")String fName,
                           @RequestParam("lName")String lName,
                           @RequestParam("gender")String gender,
                           @RequestParam("email")String email,
                           @RequestParam("password")String password,
                           @RequestParam("repeat_password")String repeat_password){
        if (bindingResult.hasErrors()) {
            return "login-register";
        }else {
            User registeredUser = new User(fName, lName, gender, email, password, "USER");
            boolean registered = userService.registerUser(registeredUser);

            if (!registered){
                bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
                return "login-register";
            }
            return "redirect:/verify-email";
        }
    }
    @GetMapping(value = "/register")
    public String register(){
        return "redirect:/login-register";
    }

    @GetMapping(value = "/user-profile")
    public String userProfile(Model model){
        User authUser = (User) userService.findLoggedInUsername();
        model.addAttribute("user", authUser);
        model.addAttribute("authUser",authUser);
        if (authUser != null && authUser.getClass().getName().contains("Hospital")){
            model.addAttribute("isHospitalLogged",true);
        }else {
            model.addAttribute("isHospitalLogged",false);
        }
        return "user-profile";
    }

    /*TODO here i have security issue where user could change id in hidden field*/
    @PostMapping(value = "/user-profile")
    public String editUserProfile(User user,
                                  RedirectAttributes redirectAttributes){

            if (!userService.editUserProfile(user)){
                redirectAttributes.addFlashAttribute("failure",true);
                return "user-profile";

            }
            redirectAttributes.addFlashAttribute("userUpdated",true);
            return "redirect:/user-profile";
    }

    @GetMapping(value = "/user-appointments")
    public String userAppointments(Model model){
        User authUser = (User) userService.findLoggedInUsername();
        model.addAttribute("user", authUser);
        model.addAttribute("authUser",authUser);
        if (authUser != null && authUser.getClass().getName().contains("Hospital")){
            model.addAttribute("isHospitalLogged",true);
        }else {
            model.addAttribute("isHospitalLogged",false);
        }
        return "user-appointments";
    }
}
