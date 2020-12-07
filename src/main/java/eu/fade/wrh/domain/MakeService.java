package eu.fade.wrh.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MakeService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehouse");
    private EntityManager em = emf.createEntityManager();

    public Make createNewMake(Make make) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(make);

        tx.commit();

        return make;
    }

    public Make updateMake(Make make) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(make);

        tx.commit();

        return make;
    }

    public void deleteMake(Make make) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.remove(make);

        tx.commit();
    }

    public Make getMakeById(int id) {
        return em.find(Make.class, id);
    }

}
