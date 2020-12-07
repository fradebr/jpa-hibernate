package eu.fade.wrh.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BranchService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehouse");
    private EntityManager em = emf.createEntityManager();


    public Branch addBranch(Branch branch) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(branch);
        tx.commit();
        return branch;
    }

    public Branch updateBranch(Branch branch) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(branch);
        tx.commit();

        return branch;
    }

    public void deleteBranch(Branch branch) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(branch);
        tx.commit();
    }

    public Branch getBranchById(int id) {
        return em.find(Branch.class, id);
    }

}
