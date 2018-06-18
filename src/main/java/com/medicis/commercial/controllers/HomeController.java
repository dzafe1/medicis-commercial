package com.medicis.commercial.controllers;


import com.medicis.commercial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String index(Model model) {
        Object authUser = userService.findLoggedInUsername();
        model.addAttribute("authUser",authUser);
        if (authUser != null && authUser.getClass().getName().contains("Hospital")){
            System.out.println("tu sam");
            model.addAttribute("isHospitalLogged",true);
        }else {
            model.addAttribute("isHospitalLogged",false);
        }
        return "index";
    }
    @GetMapping(value = "/access-denied")
    public String accessDenied(Model model){
        Object authUser = userService.findLoggedInUsername();
        model.addAttribute("authUser",authUser);
        if (authUser != null && authUser.getClass().getName().contains("Hospital")){
            model.addAttribute("isHospitalLogged",true);
        }else {
            model.addAttribute("isHospitalLogged",false);
        }
        return "404";
    }
    @GetMapping(value = "/contact")
    public String contact(Model model){
        Object authUser = userService.findLoggedInUsername();
        model.addAttribute("authUser",authUser);
        if (authUser != null && authUser.getClass().getName().contains("Hospital")){
            model.addAttribute("isHospitalLogged",true);
        }else {
            model.addAttribute("isHospitalLogged",false);
        }
        return "contact";
    }
}
