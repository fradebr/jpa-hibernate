package eu.fade.wrh;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import eu.fade.wrh.domain.Detail;

public class Main {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehouse");;
    EntityManager em;

    private void createAndUpdate() {
        em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Detail detail = new Detail();
        detail.setMaximumStock(10);
        detail.setMinimumStock(2);
        detail.setPrice(10.5);
        em.persist(detail);
        tx.commit();

        Detail detailRetrieved = em.find(Detail.class, 1);
        System.out.println(detailRetrieved.getId() + " - " +
                                   detailRetrieved.getMaximumStock() + " - " +
                                   detailRetrieved.getMinimumStock() + " - " +
                                   detailRetrieved.getPrice());

        tx.begin();
        detailRetrieved.setMaximumStock(20);
        em.persist(detailRetrieved);
        tx.commit();

        Detail detailUpdatedRetrieved = em.find(Detail.class, 1);
        System.out.println(detailUpdatedRetrieved.getId() + " - " +
                                   detailUpdatedRetrieved.getMaximumStock() + " - " +
                                   detailUpdatedRetrieved.getMinimumStock() + " - " +
                                   detailUpdatedRetrieved.getPrice());

        em.close();
        detailUpdatedRetrieved.setMaximumStock(99);

    }

    private void findDetail() {

        em = emf.createEntityManager();
        Detail detailRetrieved = em.find(Detail.class, 1);
        System.out.println(detailRetrieved.getId() + " - " +
                                   detailRetrieved.getMaximumStock() + " - " +
                                   detailRetrieved.getMinimumStock() + " - " +
                                   detailRetrieved.getPrice());

        em.close();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.createAndUpdate();
        app.findDetail();
        app.emf.close();
    }
}
