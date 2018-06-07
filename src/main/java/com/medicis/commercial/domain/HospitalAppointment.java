package com.medicis.commercial.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "appointments")
@Entity
public class HospitalAppointment implements Serializable {

    private static final long serialVersionUID = -3610761640018239560L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date created_at= new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name="approved",columnDefinition="TINYINT(1) default '1'")
    private Boolean active=true;

    @Column
    @NotNull
    private Date dateOfAppointment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diagnosis_id",nullable = false)
    private Diagnosis diagnosis;

    public HospitalAppointment() {
    }

    public HospitalAppointment(Hospital hospital, User user, Boolean active, Date dateOfAppointment,Diagnosis diagnosis) {
        this.hospital = hospital;
        this.user = user;
        this.active = active;
        this.dateOfAppointment = dateOfAppointment;
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "HospitalAppointment{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", created_at=" + created_at +
                ", active=" + active +
                ", dateOfAppointment=" + dateOfAppointment +
                '}';
    }
}
