package com.medicis.commercial.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "hospital_images")
public class HospitalsImages implements Serializable {

    private static final long serialVersionUID = -5325205516857380509L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty(message = "*Please provide image Hospital")
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "hospital_id",referencedColumnName = "id")
    private Hospital hospital;

    public HospitalsImages() {
    }

    public HospitalsImages(String path, Hospital hospital) {
        this.path = path;
        this.hospital = hospital;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "HospitalsImages{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
