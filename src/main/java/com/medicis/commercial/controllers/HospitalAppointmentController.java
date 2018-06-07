package com.medicis.commercial.controllers;

import com.medicis.commercial.domain.In.AppointmentIn;
import com.medicis.commercial.service.HospitalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class HospitalAppointmentController {
    @Autowired
    HospitalAppointmentService hospitalAppointmentService;

    @GetMapping(value = "/make-appointment")
    public String makeAppointment(Model model){
        model.addAttribute("appointment",new AppointmentIn());
        return "appointment";
    }
    @PostMapping(value = "/make-appointment")
    public String makeAppointment(@ModelAttribute("appointment")AppointmentIn appointment,
                                  BindingResult bindingResult,
                                  @RequestParam(name = "diagnosis")String diagnosis,
                                  @RequestParam(name = "dateOfAppointment")String dateOfAppointment,
                                  @PathVariable(name = "id") Long id,
                                  Model model,
                                  Principal principal,
                                  RedirectAttributes redirectAttributes){
        return "appointment";
    }
}
