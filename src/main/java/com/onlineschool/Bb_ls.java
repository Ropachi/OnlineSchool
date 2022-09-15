package com.onlineschool;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.*;

@Named
@SessionScoped
public class Bb_ls implements Serializable {

    @NotNull
    private Integer ls_id;
    private Integer st_id;
    private String ls_cos;
    private String ls_time;
    private String ls_date;
    {ls_date = "2019/01/01";}
    
    @PersistenceContext
    private EntityManager el;
    
    @EJB
    Lesson1Db ls_db;
    
    @Inject
    transient Logger log;
    private List<Lesson1> ls_list;

    private static final Map<String, String> timeItems;    
    static {
        timeItems = new LinkedHashMap<>();
        timeItems.put("17:00-17:50" ,"17:00-17:50");
        timeItems.put("18:00-18:50" ,"18:00-18:50");
        timeItems.put("19:00-19:50" ,"19:00-19:50");
        timeItems.put("20:00-20:50" ,"20:00-20:50");
        timeItems.put("21:00-21:50" ,"21:00-21:50");
    }    

    public Map<String, String>getTimeItems(){
        return timeItems;
    }
    
    private static final Map<String, String> cosItems; 
    
    static {
        cosItems = new LinkedHashMap<>();
        cosItems.put("TOEIC試験対策コース" ,"TOEIC試験対策コース");
        cosItems.put("TOEFL試験対策コース" ,"TOEFL試験対策コース");
        cosItems.put("英検対策コース" ,"英検対策コース");
        cosItems.put("ビジネス英会話会話コース" ,"ビジネス英会話会話コース");
        cosItems.put("日常会話コース" ,"日常会話コース");
    }    

    public Map<String, String>getCosItems(){
        return cosItems;
    }
    
    public List<Lesson1> getbyId(Integer login_id) {
         TypedQuery q = el.createQuery("SELECT c FROM Lesson1 c WHERE c.st_id = :lg_id", Lesson1.class);
         q.setParameter("lg_id", login_id);
         
         List<Lesson1> ls_list = q.getResultList();
         return  ls_list;
    }

    public String create(Integer login_id) {
        String rs = "";
        st_id = login_id;
        
        Lesson1 lesson = new Lesson1(ls_id, st_id, ls_cos, ls_time, ls_date);// 新規登録
        try {
            ls_db.create(lesson);
            clear();
            rs = "ls_index";
        } catch (Exception e) {
            log.fine(ls_id + "|" + e.getMessage());
            //log.log(Level.FINE, "{0}|{1}", new Object[]{ls_id, e.getMessage()});
            rs = "ls_create";
        }
        return rs;
    }
    
    public String edit(Lesson1 lesson) {	// 編集データのセット
        ls_id = lesson.getLs_id();
        ls_cos = lesson.getLs_cos();
        ls_time = lesson.getLs_time();
        ls_date = lesson.getLs_date();
        return null;
    }

    public String update(Integer login_id) {	// 更新
        String rs = "";
        st_id = login_id;
        
        Lesson1 lesson = new Lesson1(ls_id, st_id, ls_cos, ls_time, ls_date);
        try {
            ls_db.update(lesson);
            clear();
            rs = "ls_index";
        } catch (Exception e) {
            log.log(Level.FINE, "{0}|{1}", new Object[]{ls_id, e.getMessage()});
            rs = "ls_update";
        }
        return rs;
    }

    public String delete(Lesson1 lesson) {	
        ls_db.delete(lesson);
        return "ls_index";
    }

    public List<Lesson1> getAll() {	
        return ls_db.getAll();
    }

    public void clear() {	
        ls_id = null;
        st_id = null;
        ls_cos = null;
        ls_time = null;
        ls_date = null;
    }

    // setter and getter

    public Integer getLs_id() {
        return ls_id;
    }

    public void setLs_id(Integer ls_id) {
        this.ls_id = ls_id;
    }

    public String getLs_cos() {
        return ls_cos;
    }

    public void setLs_cos(String ls_cos) {
        this.ls_cos = ls_cos;
    }

    public String getLs_time() {
        return ls_time;
    }

    public void setLs_time(String ls_time) {
        this.ls_time = ls_time;
    }

    public List<Lesson1> getLs_list() {
        return ls_list;
    }

    public void setLs_list(List<Lesson1> ls_list) {
        this.ls_list = ls_list;
    }

    public String getLs_date() {
        return ls_date;
    }

    public void setLs_date(String ls_date) {
        this.ls_date = ls_date;
    }
    
}
