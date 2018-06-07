package com.medicis.commercial.repository;

import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.HospitalsImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalsImagesRepository extends JpaRepository<HospitalsImages,Long> {
    List<HospitalsImages> findAllByHospital(Hospital hospital);
}
