package com.onlineschool;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Entity;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.*;
import java.io.*;
import javax.enterprise.context.SessionScoped;
@Named
@SessionScoped
public class Bb implements Serializable {

    @NotNull
    private Integer st_id;
    @Size(max = 20, min = 1)
    private String st_name;
    @Size(max = 30, min = 1)
    private String st_psw;
    @Size(max = 30, min = 1)
    private String st_mail;

    @Size(max = 30, min = 1)
    String login_name;
    @Size(max = 30, min = 1)
    private String login_psw;

    Integer login_id;
        
    @PersistenceContext
    private EntityManager em;

    private String cos;
    
    @EJB
    Student1Db db;    
    Student1 st;    
    @Inject
    transient Logger log;

    public String login() {
        
        Query q = em.createQuery("SELECT c FROM Student1 c WHERE c.st_name = :lg_name", Student1.class);
        q.setParameter("lg_name", login_name);

        Student1 st = (Student1) q.getSingleResult();
        String rs = "";
        
        if (st != null){
            Integer st_id = st.getSt_id();
            String st_name = st.getSt_name();
            String st_psw = st.getSt_psw();

            if ((login_name.equals(st_name)) && (login_psw.equals(st_psw))) {
                login_id = st_id;

                 rs = "ls_index.xhtml";            
            } else {
                rs = "login.xhtml";
            }
        }
        return rs;
    }   
    public String logout() {
        login_id = -999;
        System.out.println("logout");
        return "index.xhtml";
    }

    public String create() {
        Student1 student = new Student1(st_id, st_name, st_psw, st_mail);// 新規登録
        try {
            db.create(student);
            clear();
        } catch (Exception e) {
            log.fine(st_id + "|" + e.getMessage());
        }
        return null;
    }
    
    public String edit(Student1 student) {	// 編集データのセット
        st_id = student.getSt_id();
        st_name = student.getSt_name();
        st_psw = student.getSt_psw();
        st_mail = student.getSt_mail();
        return null;
    }

    public String update() {	// 更新
        Student1 student = new Student1(st_id, st_name, st_psw, st_mail);
        try {
            db.update(student);
            clear();
        } catch (Exception e) {
            log.fine(st_id + "|" + e.getMessage());
        }
        return null;
    }

    public String delete(Student1 student) {	// 削除
        db.delete(student);
        return null;
    }

    public String find() {		
        Student1 m = db.find(st_id);
        if (m != null) {
            this.st_id = m.getSt_id();
            this.st_name = m.getSt_name();
            this.st_psw = m.getSt_psw();
            this.st_mail = m.getSt_mail();
        }
        return null;
    }
    
    public List<Student1> getAll() {	
        return db.getAll();
    }

    public void clear() {	
        st_id = null;
        st_name = st_psw = st_mail = null;
    }

    // setter and getter
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

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_psw() {
        return login_psw;
    }

    public void setLogin_psw(String login_psw) {
        this.login_psw = login_psw;
    }    

    public Integer getLogin_id() {
        return login_id;
    }

    public void setLogin_id(Integer login_id) {
        this.login_id = login_id;
    }

}
