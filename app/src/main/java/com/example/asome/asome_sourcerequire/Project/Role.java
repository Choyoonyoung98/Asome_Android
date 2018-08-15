package com.example.asome.asome_sourcerequire.Project;

import java.io.Serializable;

public class Role implements Serializable{
    String role_name,role_end_date, role_start_date,role_status,role_real_name;

    public Role(String role_name, String role_start_date, String role_end_date, String role_status, String role_real_name) {
        this.role_name = role_name;
        this.role_end_date = role_end_date;
        this.role_start_date = role_start_date;
        this.role_status = role_status;
        this.role_real_name = role_real_name;
    }

    public Role(String role_name, String role_start_date,String role_end_date) {
        this.role_name = role_name;
        this.role_end_date = role_end_date;
        this.role_start_date = role_start_date;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_end_date() {
        return role_end_date;
    }

    public void setRole_end_date(String role_end_date) {
        this.role_end_date = role_end_date;
    }

    public String getRole_start_date() {
        return role_start_date;
    }

    public void setRole_start_date(String role_start_date) {
        this.role_start_date = role_start_date;
    }
}
