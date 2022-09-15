package com.onlineschool;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Student1Db {

    @PersistenceContext
    private EntityManager em;

    public void create(Student1 student) {
        em.persist(student);
    }

    public void update(Student1 student) {
        em.merge(student);
    }

    public void delete(Student1 student) {
        em.remove(em.merge(student));  // mergeしてから削除する
    }

    public Student1 find(Integer key) {
        return em.find(Student1.class, key);
    }

    public Student1 find2(String name) {
        return em.find(Student1.class, name);
    }

    public List<Student1> getAll() {
        return em.createQuery("SELECT c FROM Student1 c").getResultList();
    }
}
