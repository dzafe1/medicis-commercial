package com.medicis.commercial.domain.In;

import java.io.Serializable;

public class UserIn implements Serializable {

    private static final long serialVersionUID = 6822401712887671616L;

    private Long id;

    private String fName;

    private String lName;

    private String email;

    private String password;

    public UserIn() {
    }

    public UserIn(Long id, String fName, String lName, String email, String password) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
