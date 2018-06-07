package com.medicis.commercial.controllers;

import com.medicis.commercial.domain.User;
import com.medicis.commercial.domain.VerificationToken;
import com.medicis.commercial.service.TokenService;
import com.medicis.commercial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class TokenController {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;

    @GetMapping(path = "/verify-email")
    public String verifyEmail(Model model){
        model.addAttribute("token",new VerificationToken());
        return "email-verify";
    }
    @PostMapping(path = "/verify-email")
    public String verifyEmail(@Valid VerificationToken verificationToken,
                              BindingResult bindingResult,
                              @RequestParam String token,
                              RedirectAttributes redirectAttributes,
                              HttpServletRequest request,Model model){
        if (bindingResult.hasErrors()) {
            return "email-verify";
        }
        User user = tokenService.validateEmail(token);
        if (user != null) {
            model.addAttribute("tokenVerified",true);
            return "email-verify";
        }else {
            bindingResult.rejectValue("token", "error.token", "Wrong token!");
            return "email-verify";
        }
    }
}
