package com.medicis.commercial.service;

import com.medicis.commercial.domain.Diagnosis;
import com.medicis.commercial.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    DiagnosisRepository diagnosisRepository;


    public List<Diagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();
    }

    Diagnosis getOneByName(String diagnosis) {
        return diagnosisRepository.getByDiagnosis(diagnosis);
    }
}
