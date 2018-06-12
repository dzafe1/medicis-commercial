package com.medicis.commercial.controllers;


import com.medicis.commercial.domain.HospitalEmployee;
import com.medicis.commercial.service.HospitalEmployeeService;
import com.medicis.commercial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;


@Controller
public class HospitalEmployeeController {

    @Autowired
    HospitalEmployeeService hospitalEmployeeService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/employee/{id}")
    @ResponseBody
    public HospitalEmployee getEmployee(@PathVariable(name = "id")Long id){
        return hospitalEmployeeService.getHospitalEmployeeById(id);
    }

    @PostMapping(value = "/edit-hospital-employee")
    public String editHospitalEmployee(@RequestParam(name = "employeeId")String employeeId, @RequestParam(name = "email")String email,
                                       @RequestParam(name = "fName")String fName, @RequestParam(name = "lName")String lName,
                                       @RequestParam(name = "title")String title, @RequestParam(name = "employeeMessage")String employeeMessage, RedirectAttributes redirectAttributes){
        Map<String,String> parameters = new HashMap<>();
        parameters.put("employeeId",employeeId);
        parameters.put("email",email);
        parameters.put("fName",fName);
        parameters.put("lName",lName);
        parameters.put("title",title);
        parameters.put("employeeMessage",employeeMessage);
        if (hospitalEmployeeService.updateEmployee(parameters)) {
            redirectAttributes.addFlashAttribute("updateSuccessfulEmployee",true);
            return "redirect:/hospital-profile";
        }else {
            redirectAttributes.addFlashAttribute("updateUnsuccessfulEmployee",true);
            return "redirect:/hospital-profile";
        }
    }
}
