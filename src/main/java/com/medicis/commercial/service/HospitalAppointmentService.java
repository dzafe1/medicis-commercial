package com.medicis.commercial.service;

import com.medicis.commercial.domain.Diagnosis;
import com.medicis.commercial.domain.HospitalAppointment;
import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.In.AppointmentIn;
import com.medicis.commercial.domain.User;
import com.medicis.commercial.repository.HospitalAppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class HospitalAppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    HospitalAppointmentRepository hospitalAppointmentRepository;

    @Autowired
    DiagnosisService diagnosisService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    UserService userService;

    public List<HospitalAppointment> getAppointmentsByHospital(Hospital hospital){
        return hospitalAppointmentRepository.findAllByHospital(hospital);
    }

    public void createAppointment(Long hospitalId, AppointmentIn appointmentIn) {
        DateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date desiredDate = null;
        try {
            desiredDate = format.parse(appointmentIn.getDateOfAppointment());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Diagnosis diagnosis = diagnosisService.getOneByName(appointmentIn.getDiagnosis());
        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        User user = (User) userService.findLoggedInUsername();
        HospitalAppointment hospitalAppointment = hospitalAppointmentRepository.save(new HospitalAppointment(hospital,user,true,desiredDate,diagnosis));
        logger.info("Creating appointment successfully: {}" + hospitalAppointment);
    }
    public List<HospitalAppointment> getAppointmentsByUser(User user){
        return hospitalAppointmentRepository.findAllByUser(user);
    }
}
