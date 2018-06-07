package com.medicis.commercial.controllers;

import com.medicis.commercial.domain.Diagnosis;
import com.medicis.commercial.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiagnosisController {
    @Autowired
    DiagnosisService diagnosisService;

    @GetMapping(value = "/diagnosis")
    @ResponseBody
    public List<Diagnosis> getAllDiagnosis(){
        return diagnosisService.getAllDiagnosis();
    }
}
