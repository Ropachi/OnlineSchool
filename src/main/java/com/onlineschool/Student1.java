package com.onlineschool;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.*;
 
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Student1 implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer st_id;
    private String st_name;
    private String st_psw;
    private String st_mail;

    public Student1() {
    }

    public Student1(Integer st_id, String st_name, String st_psw, String st_mail) {
        this.st_id = st_id;
        this.st_name = st_name;
        this.st_psw = st_psw;
        this.st_mail = st_mail;
    }

    public Integer getSt_id() {
        return st_id;
    }

    public void setSt_id(Integer st_id) {
        this.st_id = st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_psw() {
        return st_psw;
    }

    public void setSt_psw(String st_psw) {
        this.st_psw = st_psw;
    }

    public String getSt_mail() {
        return st_mail;
    }

    public void setSt_mail(String st_mail) {
        this.st_mail = st_mail;
    }    
}
