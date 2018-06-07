package com.medicis.commercial.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "hospitals")
@SQLDelete(sql="Update hospital SET is_active = 0 where id=?")
public class Hospital implements Serializable {

    private static final long serialVersionUID = -8682536770308257120L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition="VARCHAR(255)")
    @NotEmpty(message = "*Please provide password of Hospital")
    private String password;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date created_at = new Date();

    @Column(nullable = false)
    @NotEmpty(message = "*Please provide full name of Hospital")
    private String fullName;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;


    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false,columnDefinition="VARCHAR(8000)")
    private String aboutHospital;

    @Column(nullable = false)
    private int amountOfBeds;

    @Column(nullable = false)
    private int yearlyPatients;

    @Column(name="is_active",columnDefinition="TINYINT(1) default '1'")
    private Boolean active=true;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id",referencedColumnName = "id")
    private List<HospitalsImages> hospitalsImages = new ArrayList<HospitalsImages>();

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private List<HospitalEmployee> hospitalsEmployee = new ArrayList<HospitalEmployee>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hospital")
    private List<HospitalAppointment> hospitalAppointment = new ArrayList<HospitalAppointment>();

    public Hospital() {
    }

    public Hospital(String email, String password, String fullName, String country, String city, String postalCode, String address, String aboutHospital, int amountOfBeds, int yearlyPatients, Boolean active) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
        this.aboutHospital = aboutHospital;
        this.amountOfBeds = amountOfBeds;
        this.yearlyPatients = yearlyPatients;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAboutHospital() {
        return aboutHospital;
    }

    public void setAboutHospital(String aboutHospital) {
        this.aboutHospital = aboutHospital;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<HospitalsImages> getHospitalsImages() {
        return hospitalsImages;
    }

    public void setHospitalsImages(List<HospitalsImages> hospitalsImages) {
        this.hospitalsImages = hospitalsImages;
    }

    public List<HospitalEmployee> getHospitalsEmployee() {
        return hospitalsEmployee;
    }

    public void setHospitalsEmployee(List<HospitalEmployee> hospitalsEmployee) {
        this.hospitalsEmployee = hospitalsEmployee;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAmountOfBeds() {
        return amountOfBeds;
    }

    public void setAmountOfBeds(int amountOfBeds) {
        this.amountOfBeds = amountOfBeds;
    }

    public int getYearlyPatients() {
        return yearlyPatients;
    }

    public void setYearlyPatients(int yearlyPatients) {
        this.yearlyPatients = yearlyPatients;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public List<HospitalAppointment> getHospitalAppointment() {
        return hospitalAppointment;
    }

    public void setHospitalAppointment(List<HospitalAppointment> hospitalAppointment) {
        this.hospitalAppointment = hospitalAppointment;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created_at=" + created_at +
                ", fullName='" + fullName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", address='" + address + '\'' +
                ", aboutHospital='" + aboutHospital + '\'' +
                ", amountOfBeds=" + amountOfBeds +
                ", yearlyPatients=" + yearlyPatients +
                ", active=" + active +
                '}';
    }
}
