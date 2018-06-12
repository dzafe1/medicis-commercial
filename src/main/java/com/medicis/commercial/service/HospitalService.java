package com.medicis.commercial.service;

import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.User;
import com.medicis.commercial.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    UserService userService;

    public List<Hospital> getAllHospitals(){
        return hospitalRepository.getAllHospitals();
    }

    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findOneById(id);
    }


    public Hospital findLoggedInHospital() {
        return (Hospital)userService.findLoggedInUsername();
    }

    public Hospital getHospitalByEmail(String username) {
        return hospitalRepository.findOneByEmail(username);
    }
}
