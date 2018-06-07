package com.medicis.commercial.controllers;


import com.medicis.commercial.domain.User;
import com.medicis.commercial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String index(Model model) {
        User authUser = userService.findLoggedInUsername();
        model.addAttribute("authUser",authUser);
        return "index";
    }
    @GetMapping(value = "/access-denied")
    public String accessDenied(){
        return "404";
    }
}
