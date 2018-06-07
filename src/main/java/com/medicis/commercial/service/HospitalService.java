package com.medicis.commercial.service;

import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    public List<Hospital> getAllHospitals(){
        return hospitalRepository.getAllHospitals();
    }

    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findOneById(id);
    }
}
