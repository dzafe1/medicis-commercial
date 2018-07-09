package com.medicis.commercial.service;

import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.User;
import com.medicis.commercial.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @Modifying
    public boolean editHospitalProfile(Hospital hospital) {
        Hospital authHospital = findLoggedInHospital();
        if (!authHospital.getId().equals(hospital.getId())) return false;

        if (!hospital.getFullName().equals(authHospital.getFullName()) && !hospital.getFullName().isEmpty()){
            authHospital.setFullName(hospital.getFullName());
        }
        if (!hospital.getAddress().equals(authHospital.getAddress()) && !hospital.getAddress().isEmpty()){
            authHospital.setAddress(hospital.getAddress());
        }
        if (!hospital.getCity().equals(authHospital.getCity()) && !hospital.getCity().isEmpty()){
            authHospital.setCity(hospital.getCity());
        }
        if (!hospital.getPostalCode().equals(authHospital.getPostalCode()) && !hospital.getPostalCode().isEmpty()){
            authHospital.setPostalCode(hospital.getPostalCode());
        }
        if (hospital.getAmountOfBeds() != authHospital.getAmountOfBeds()){
            authHospital.setAmountOfBeds(hospital.getAmountOfBeds());
        }
        if (!hospital.getCountry().equals(authHospital.getCountry()) && !hospital.getCountry().isEmpty()){
            authHospital.setCountry(hospital.getCountry());
        }
        if (!hospital.getAboutHospital().equals(authHospital.getAboutHospital()) && !hospital.getAboutHospital().isEmpty()){
            authHospital.setAboutHospital(hospital.getAboutHospital());
        }

        hospitalRepository.saveAndFlush(authHospital);
        return true;
    }
    @Modifying
    public Boolean changePassword(Map<String, String> passwords){
        String currentPassword = passwords.get("currentPassword");
        String newPassword = passwords.get("newPassword");
        String repeatPassword = passwords.get("repeatPassword");
        if(!newPassword.equals(repeatPassword)){
            System.out.println("prvi false");
            return false;
        }
        Hospital authHospital = findLoggedInHospital();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(newPassword,authHospital.getPassword())
                && bCryptPasswordEncoder.matches(currentPassword,authHospital.getPassword())){
            authHospital.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }else{
            return false;
        }
        return true;
    }
    private void changeHospitalPrincipalPassword(String password){
        Principal principal=(Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public List<Hospital> searchHospitals(String country, String city, String diagnosis) {
        if (!country.isEmpty() && city.isEmpty() && diagnosis.isEmpty()){
            return hospitalRepository.getAllByCountry(country);
        }
        if (!city.isEmpty() && country.isEmpty() && diagnosis.isEmpty()){
            return hospitalRepository.getAllByCity(city);
        }
        if (!city.isEmpty() && !country.isEmpty()){
            return hospitalRepository.getAllByCityAndCountry(city,country);
        }
        else return Collections.emptyList();
    }
}
