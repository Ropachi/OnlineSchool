package com.onlineschool;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Lesson1Db {

    @PersistenceContext
    private EntityManager em;

    public void create(Lesson1 lesson1) {
        em.persist(lesson1);
    }

    public void update(Lesson1 lesson1) {
        em.merge(lesson1);
    }

    public void delete(Lesson1 lesson1) {
        em.remove(em.merge(lesson1));  // mergしてから削除する
    }

    public List<Lesson1> getAll() {
        return em.createQuery("SELECT c FROM Lesson1 c").getResultList();
    }
}
