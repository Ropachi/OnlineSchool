package com.onlineschool;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Lesson1 implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ls_id;    
    private Integer st_id;
    private String ls_cos;
    private String ls_time;
    private String ls_date;

    public Lesson1() {
    }

    public Lesson1(Integer ls_id, Integer st_id, String ls_cos, String ls_time, String ls_date) {
        this.ls_id = ls_id;
        this.st_id = st_id;
        this.ls_cos = ls_cos;
        this.ls_time = ls_time;
        this.ls_date = ls_date;
    }

    public Integer getLs_id() {
        return ls_id;
    }

    public void setLs_id(Integer ls_id) {
        this.ls_id = ls_id;
    }

    public Integer getSt_id() {
        return st_id;
    }

    public void setSt_id(Integer login_id) {
        this.st_id = login_id;
    }

    public String getLs_cos() {
        return ls_cos;
    }

    public void setLs_cos(String ls_cos) {
        this.ls_cos = ls_cos;
    }

    public String getLs_date() {
        return ls_date;
    }

    public void setLs_date(String ls_date) {
        this.ls_date = ls_date;
    }

    public String getLs_time() {
        return ls_time;
    }

    public void setLs_time(String ls_time) {
        this.ls_time = ls_time;
    }
}
