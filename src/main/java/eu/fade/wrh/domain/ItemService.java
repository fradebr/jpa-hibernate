package eu.fade.wrh.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ItemService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehouse");
    private EntityManager em = emf.createEntityManager();

    public Item createNewItem(Item item) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(item);

        tx.commit();

        return item;
    }

    public void deleteItem(Item item) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.remove(item);

        tx.commit();
    }

    public Item getItemById(int id) {
        return em.find(Item.class, id);
    }

}
