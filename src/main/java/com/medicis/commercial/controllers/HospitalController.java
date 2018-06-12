package com.medicis.commercial.controllers;


import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.In.AppointmentIn;
import com.medicis.commercial.service.HospitalAppointmentService;
import com.medicis.commercial.service.HospitalEmployeeService;
import com.medicis.commercial.service.HospitalService;
import com.medicis.commercial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;


@Controller
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @Autowired
    HospitalEmployeeService hospitalEmployeeService;

    @Autowired
    HospitalAppointmentService hospitalAppointmentService;

    @Autowired
    UserService userService;


    @GetMapping(value = "/hospitals")
    public String hospitals(Model model){
        model.addAttribute("hospitals",hospitalService.getAllHospitals());
        Object authUser = userService.findLoggedInUsername();
        model.addAttribute("authUser",authUser);
        return "hospitals";
    }
    @GetMapping(value = "/hospital/{id}")
    public String hospital(Model model,@PathVariable Long id){
        model.addAttribute("isHospital",true);
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        Object object = userService.findLoggedInUsername();
        model.addAttribute("authUser",object);
        if (object != null && object.getClass().getName().contains("Hospital")){
            model.addAttribute("isHospitalLogged",true);
        }
        return "hospital";
    }

    @GetMapping(value = "/hospital/{id}/employees")
    public String hospitalsEmployees(Model model,@PathVariable Long id){
        Hospital hospital = hospitalService.getHospitalById(id);
        model.addAttribute("isEmployee",true);
        model.addAttribute("hospital",hospital);
        model.addAttribute("hospitalEmployees",hospitalEmployeeService.getEmployeesByHospital(hospital));
        Object object = userService.findLoggedInUsername();
        model.addAttribute("authUser",object);
        System.out.println(object.getClass().getName());
        if (object != null && object.getClass().getName().contains("Hospital")){
            model.addAttribute("isHospitalLogged",true);
        }
        return "hospital";
    }

    @GetMapping(value = "/hospital/{id}/appointment")
    @PreAuthorize("hasRole('ROLE_ANONYMOUS') or (hasAuthority('USER')) && (not hasAuthority('ROLE_HOSPITAL'))")
    public String hospitalAppointment(Model model,@PathVariable(name = "id") Long id){
        model.addAttribute("isAppointment",true);
        model.addAttribute("appointment",new AppointmentIn());
        model.addAttribute("hospital",hospitalService.getHospitalById(id));
        Object object = userService.findLoggedInUsername();
        model.addAttribute("authUser",object);
        if (object != null && object.getClass().getName().contains("Hospital")){
           model.addAttribute("isHospitalLogged",true);
        }
        return "hospital";
    }

    @PostMapping(value = "/hospital/{id}/appointment")
    @PreAuthorize("hasRole('USER') or hasRole('ROLE_ANONYMOUS')")
    public String makeHospitalAppointment(@ModelAttribute("appointment")AppointmentIn appointment,
                                          BindingResult bindingResult,
                                          @RequestParam(name = "diagnosis")String diagnosis,
                                          @RequestParam(name = "dateOfAppointment")String dateOfAppointment,
                                          @PathVariable(name = "id") Long id,
                                          Model model,
                                          Principal principal,
                                          RedirectAttributes redirectAttributes){
       if (diagnosis.isEmpty() || bindingResult.hasErrors()) {
            bindingResult.rejectValue("diagnosis", "error.appointment", "Please fill all fields!");
            model.addAttribute("isAppointment",true);
            model.addAttribute("hospital",hospitalService.getHospitalById(id));
            return "hospital";
        }
        hospitalAppointmentService.createAppointment(id, appointment);
        redirectAttributes.addFlashAttribute("appointmentCreated", "Appointment successfully created!");
        return "redirect:/hospital/" + id + "/appointment";
    }
    @GetMapping(value = "/hospitals-api")
    @ResponseBody
    public List<Hospital> getAllHospitals(){
        return hospitalService.getAllHospitals();
    }

    @GetMapping(value = "/hospital-appointments")
    public String hospitalAppointments(Model model){
        Object authUser = userService.findLoggedInUsername();
        model.addAttribute("authUser",authUser);
        return "hospital-appointments";
    }

    @GetMapping(value = "/hospital-profile")
    public String hospitalProfile(Model model){
        Hospital hospital = hospitalService.findLoggedInHospital();
        model.addAttribute("authUser",hospital);
        model.addAttribute("hospital",hospital);
        model.addAttribute("hospitalEmployees",hospitalEmployeeService.getEmployeesByHospital(hospital));
        return "hospital-profile";
    }
}