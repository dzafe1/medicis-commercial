package com.medicis.commercial.repository;

import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.HospitalEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalEmployeeRepository extends JpaRepository<HospitalEmployee,Long> {
    List<HospitalEmployee> findAllByHospital(Hospital hospital);
    HospitalEmployee findOneById(Long id);
}
