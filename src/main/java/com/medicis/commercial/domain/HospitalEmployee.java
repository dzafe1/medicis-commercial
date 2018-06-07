package com.medicis.commercial.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "hospital_employees")
@SQLDelete(sql="Update hospital_employee SET is_active = 0 where id=?")
public class HospitalEmployee implements Serializable {

    private static final long serialVersionUID = -4131529789842364135L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide doctor's First name!")
    @Size(min=2, max=30,message = "First name must be greater then 2 characters!")
    private String fName;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide doctor's Last name!")
    @Size(min=2, max=30,message = "Last name must be greater then 2 characters!")
    private String lName;

    @Column(unique = true, nullable = false)
    @Email(message = "Please provide a valid Email!")
    @NotEmpty(message = "Please provide doctor's Email!")
    @Size(min=2, max=30,message = "Email must be greater then 2 characters")
    private String email;

    @Column(name = "img_path")
    private String imgPath;

    @Column(nullable = false)
    private String title;

    private Date dob;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date created_at= new Date();

    @LastModifiedDate
    private Date updated_at= new Date();

    @Column(name="is_active",columnDefinition="TINYINT(1) default '1'")
    private Boolean active=true;

    @Column(columnDefinition="MEDIUMTEXT")
    private String employeeMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    @JsonBackReference
    private Hospital hospital;

    public HospitalEmployee() {
    }

    public HospitalEmployee(String fName, String lName, String email, String employeeMessage, String imgPath, String title, Date dob, Boolean active, Hospital hospital) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.employeeMessage = employeeMessage;
        this.imgPath = imgPath;
        this.title = title;
        this.dob = dob;
        this.active = active;
        this.hospital = hospital;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmployeeMessage() {
        return employeeMessage;
    }

    public void setEmployeeMessage(String employeeMessage) {
        this.employeeMessage = employeeMessage;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "HospitalEmployee{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", title='" + title + '\'' +
                ", dob=" + dob +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", active=" + active +
                ", employeeMessage='" + employeeMessage + '\'' +
                '}';
    }
}
