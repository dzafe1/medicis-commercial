package com.medicis.commercial.repository;

import com.medicis.commercial.domain.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {

    List<Diagnosis> findAll();

    Diagnosis getByDiagnosis(String diagnosis);

}
