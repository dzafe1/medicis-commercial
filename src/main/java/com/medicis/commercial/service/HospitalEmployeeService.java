package com.medicis.commercial.service;

import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.HospitalEmployee;
import com.medicis.commercial.repository.HospitalEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HospitalEmployeeService {
    @Autowired
    HospitalEmployeeRepository hospitalEmployeeRepository;

    public List<HospitalEmployee> getEmployeesByHospital(Hospital hospital){
        return hospitalEmployeeRepository.findAllByHospital(hospital);
    }
    public HospitalEmployee getHospitalEmployeeById(Long id){
        return hospitalEmployeeRepository.findOneById(id);
    }

    public Boolean updateEmployee(Map<String,String> parameters) {
        Long id;
        try {
           id = Long.parseLong(parameters.get("employeeId"));
        }catch (NumberFormatException e){
            return false;
        }

        HospitalEmployee hospitalEmployee = hospitalEmployeeRepository.findOneById(id);
        if (!hospitalEmployee.getfName().equals(parameters.get("fName"))) hospitalEmployee.setfName(parameters.get("fName"));
        if (!hospitalEmployee.getlName().equals(parameters.get("lName"))) hospitalEmployee.setlName(parameters.get("lName"));
        if (!hospitalEmployee.getEmail().equals(parameters.get("email"))) hospitalEmployee.setEmail(parameters.get("email"));
        if (!hospitalEmployee.getTitle().equals(parameters.get("title"))) hospitalEmployee.setTitle(parameters.get("title"));
        if (!hospitalEmployee.getEmployeeMessage().equals(parameters.get("employeeMessage"))) hospitalEmployee.setEmployeeMessage(parameters.get("employeeMessage"));
        hospitalEmployeeRepository.saveAndFlush(hospitalEmployee);
        return true;
    }
}
