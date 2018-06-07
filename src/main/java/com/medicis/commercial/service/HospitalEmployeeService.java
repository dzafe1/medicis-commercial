package com.medicis.commercial.service;

import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.HospitalEmployee;
import com.medicis.commercial.repository.HospitalEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalEmployeeService {
    @Autowired
    HospitalEmployeeRepository hospitalEmployeeRepository;

    public List<HospitalEmployee> getEmployeesByHospital(Hospital hospital){
        return hospitalEmployeeRepository.findAllByHospital(hospital);
    }
}
