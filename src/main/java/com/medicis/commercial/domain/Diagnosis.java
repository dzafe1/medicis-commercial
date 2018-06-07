package com.medicis.commercial.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 4105793970036203518L;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String diagnosis;

    @Column
    private Integer daysForTreatment;

    @OneToMany(mappedBy = "diagnosis",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<HospitalAppointment> hospitalAppointment;

    public Diagnosis() {
    }

    public Diagnosis(String diagnosis, Integer daysForTreatment) {
        this.diagnosis = diagnosis;
        this.daysForTreatment = daysForTreatment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Integer getDaysForTreatment() {
        return daysForTreatment;
    }

    public void setDaysForTreatment(Integer daysForTreatment) {
        this.daysForTreatment = daysForTreatment;
    }

    public List<HospitalAppointment> getHospitalAppointment() {
        return hospitalAppointment;
    }

    public void setHospitalAppointment(List<HospitalAppointment> hospitalAppointment) {
        this.hospitalAppointment = hospitalAppointment;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", daysForTreatment=" + daysForTreatment +
                '}';
    }
}
