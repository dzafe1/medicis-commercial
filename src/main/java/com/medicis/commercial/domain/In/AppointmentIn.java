package com.medicis.commercial.domain.In;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class AppointmentIn implements Serializable {

    private static final long serialVersionUID = -8293594131132370295L;


    @NotNull
    @NotEmpty
    private String diagnosis;

    private String dateOfAppointment;

    private String hospital;

    public AppointmentIn() {
    }

    public AppointmentIn( String diagnosis, String dateOfAppointment) {
        this.diagnosis = diagnosis;
        this.dateOfAppointment = dateOfAppointment;
    }

    public AppointmentIn(String diagnosis, String dateOfAppointment, String hospitalId) {
        this.diagnosis = diagnosis;
        this.dateOfAppointment = dateOfAppointment;
        this.hospital = hospitalId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }


    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "AppointmentIn{" +
                ", diagnosis=" + diagnosis +
                ", dateOfAppointment=" + dateOfAppointment +
                '}';
    }
}
