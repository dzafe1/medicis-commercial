package com.medicis.commercial.repository;

import com.medicis.commercial.domain.HospitalAppointment;
import com.medicis.commercial.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalAppointmentRepository extends JpaRepository<HospitalAppointment,Long> {

    List<HospitalAppointment> findAllByHospital(Hospital hospital);
}
