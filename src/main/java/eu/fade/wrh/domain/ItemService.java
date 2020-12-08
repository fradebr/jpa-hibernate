package eu.fade.wrh.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

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


    public List<Item> getAllItems() {
        TypedQuery<Item> query = em.createQuery("select item from Item as item", Item.class);

        return query.getResultList();
    }

}
