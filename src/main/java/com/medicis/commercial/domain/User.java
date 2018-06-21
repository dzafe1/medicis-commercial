package com.medicis.commercial.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@SQLDelete(sql="Update user SET is_active = 0 where id=?")
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 431167865809602897L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide your First name!")
    @Size(min=2, max=30,message = "First name must be greater then 2 characters!")
    private String fName;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide your Last name!")
    @Size(min=2, max=30,message = "Last name must be greater then 2 characters!")
    private String lName;

    @Column(nullable = false)
    @Size(min=1, max=3)
    private String gender;

    @Column(nullable = false)
    @Email(message = "Please provide a valid email!")
    @NotEmpty(message = "Please provide your email!")
    @Size(min=2, max=30,message = "Email must be greater then 2 characters")
    private String email;

    /*TODO moras dodati pravi broj,odnosno validaciju*/
    @Column(columnDefinition="varchar(25)")
    //@Pattern(regexp = "^(?:\\+\\d{1,3}|0\\d{1,3}|00\\d{1,2})?(?:\\s?\\(\\d+\\))?(?:[-\\/\\s.]|\\d)+$", message="Invalid phone number!")
    private String phoneNumber;

    @Column
    private String phoneToken;

    @NotNull
    @Column(columnDefinition="TINYINT(1) default 0")
    private Boolean verifiedPhoneNumber=false;

    @Column(nullable = false)
    @Size(min=6, max=255,message = "Minimum 6 length!")
    @NotEmpty(message = "Please provide your password!")
    private String password;

    @Transient
    private String repeatPassword;

    @Column(name = "img_path")
    private String imgPath;

    private Date dob;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date created_at= new Date();

    @LastModifiedDate
    private Date updated_at= new Date();

    @Column(name="is_active",columnDefinition="TINYINT(1) default '1'")
    private Boolean active=true;

    @Column(name="role",columnDefinition="VARCHAR(45) default 'USER'")
    private String role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private VerificationToken verificationToken;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<HospitalAppointment> hospitalAppointment;


    public Boolean getActive() {
        return active;
    }

    public User() {}


    public User(String fName, String lName, String gender, String email, String password, String imgPath, Boolean active, String role) {
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.imgPath = imgPath;
        this.active = active;
        this.role=role;
        this.phoneNumber="Phone number";
    }

    public User(String fName, String lName, String gender, String email, String password, String role){
        this.fName = fName;
        this.lName =  lName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.active = false;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
    @PreUpdate
    public void setUpdated_at() {
        this.updated_at = new Date();
    }

    public Boolean getactive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }

    public List<HospitalAppointment> getHospitalAppointment() {
        return hospitalAppointment;
    }

    public void setHospitalAppointment(List<HospitalAppointment> hospitalAppointment) {
        this.hospitalAppointment = hospitalAppointment;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneToken() {
        return phoneToken;
    }

    public void setPhoneToken(String phoneToken) {
        this.phoneToken = phoneToken;
    }

    public Boolean getVerifiedPhoneNumber() {
        return verifiedPhoneNumber;
    }

    public void setVerifiedPhoneNumber(Boolean verifiedPhoneNumber) {
        this.verifiedPhoneNumber = verifiedPhoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", phoneToken='" + phoneToken + '\'' +
                ", verifiedPhoneNumber=" + verifiedPhoneNumber +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", dob=" + dob +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", active=" + active +
                ", role='" + role + '\'' +
                '}';
    }
}
