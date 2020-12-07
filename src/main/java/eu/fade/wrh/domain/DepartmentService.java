package eu.fade.wrh.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DepartmentService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehouse");
    private EntityManager em = emf.createEntityManager();

    public Department createNewDepartment(Department department) {
        if(em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(department);
        tx.commit();

        return department;
    }

    public Department getDepartmentById(int id) {
        if(em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }

        return em.find(Department.class, id);
    }
}
