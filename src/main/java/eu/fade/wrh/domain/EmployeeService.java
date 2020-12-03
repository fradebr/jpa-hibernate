package eu.fade.wrh.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehouse");
    private EntityManager em = emf.createEntityManager();

    public Employee createNewEmployee(Employee employee) {
        if(em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(employee);
        tx.commit();

        return employee;
    }

    public Employee getEmployeeById(int id) {
        if(em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }

        return em.find(Employee.class, id);
    }
}
