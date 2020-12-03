package eu.fade.wrh;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import eu.fade.wrh.domain.Item;
import eu.fade.wrh.domain.Make;

public class Main {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehouse");;
    EntityManager em;


    private Item createNewItem(Item item) {
        System.out.println("Start createNewItem ...");
        em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(item);
        tx.commit();
        System.out.println(item);
        em.close();
        System.out.println("End createNewItem ...");
        return item;
    }

    private Item findItemById(int id) {
        System.out.println("Start findItem ...");
        em = emf.createEntityManager();
        Item retrievedItem = em.find(Item.class, id);
        if (retrievedItem != null) {
            System.out.println(retrievedItem);
        } else {
            System.out.println("Item with id " + id + " could not be found");
        }
        System.out.println("End findItem ...");
        em.close();
        return retrievedItem;
    }

    private void getReference(int id) {
        System.out.println("Start getReference ...");
        em = emf.createEntityManager();
        Item retrievedItem = em.getReference(Item.class, id);
        System.out.println("No SQL executed so far ...");
        System.out.println(retrievedItem);
        System.out.println("Now the SQL is executed");
        System.out.println("End getReference ...");
        em.close();
    }

    private void removeItem(Item item) {
        System.out.println("Start removeItem ...");
        em = emf.createEntityManager();
        // Merge is necessary here as the em is closed everytime, so the persistence context is cleared
        Item itemToRemove = em.merge(item);
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.remove(itemToRemove);
        tx.commit();
        System.out.println("End removeItem ...");
        em.close();
    }

    private void detach(int id) {
        System.out.println("Start detach ...");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Item item = em.find(Item.class, id);
        em.detach(item);
        item.setName("Updated name");

        try {
            tx.begin();
            em.persist(item);
            tx.commit();
        } catch (Exception ex) {
            System.out.println("This will end in an exception as the persist-method will try " +
                                       "to create a new record instead of updating the existing one");
        }
        System.out.println("End detach ...");
        em.close();
    }

    private void checkPersistenceContext(int id) {
        System.out.println("Start checkPersistenceContext ...");
        em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Item item = em.find(Item.class, id);
        if(em.contains(item)) {
            System.out.println(item + " is managed");
        } else {
            System.out.println(item + " is unmanaged");
        }
        System.out.println("End checkPersistenceContext ...");
        em.close();
    }

    private void withFlushModeCommit(int id, int id2) {
        System.out.println("Start withFlushModeCommit ...");
        em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Item item = em.find(Item.class, id);
        item.setName("Updated name - commit");
        Item item2 = em.find(Item.class, id2);
        System.out.println("Item2 = " + item2);
        System.out.println("Committing the transaction now");
        em.persist(item);
        tx.commit();

        System.out.println("End withFlushModeCommit ...");
        em.close();
    }

    private void withFlushModeAuto(int id, int id2) {
        System.out.println("Start withFlushModeAuto ...");
        em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.AUTO);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Item item = em.find(Item.class, id);
        item.setName("Updated name - auto");
        Item item2 = em.find(Item.class, id2);
        System.out.println("Item2 = " + item2);
        System.out.println("Committing the transaction now");
        em.persist(item);
        tx.commit();

        System.out.println("End withFlushModeAuto ...");
        em.close();
    }

    private void createAndUpdateMake() {
        Make make = new Make();
        make.setName("Make");
        make.setOrderAmount(25);
        make.setDiscount(10.0);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(make);
        tx.commit();
        System.out.println(make);

        make.setOrderAmount(35);
        tx.begin();
        em.persist(make);
        tx.commit();
        System.out.println(make);

        Make make2 = new Make();
        make.setName("Make 2");
        make.setOrderAmount(10);
        make.setDiscount(5.0);
        tx.begin();
        em.persist(make2);
        tx.commit();
        System.out.println(make2);
        em.close();
    }

    public static void main(String[] args) {
        Main app = new Main();
        Item newItem = new Item();
        newItem.setId(2);
        newItem.setMake("Make");
        newItem.setCurrentStock(88);
        newItem.setName("Short screw");
        newItem.setType("SCREWS");

        app.createNewItem(newItem);
        Item foundItem = app.findItemById(newItem.getId());
        app.getReference(newItem.getId());
        app.removeItem(foundItem);
        app.findItemById(foundItem.getId());

        newItem = app.createNewItem(newItem);
        app.detach(newItem.getId());
        app.checkPersistenceContext(newItem.getId());

        Item anotherNewItem = new Item();
        anotherNewItem.setId(3);
        anotherNewItem.setMake("Make");
        anotherNewItem.setCurrentStock(50);
        anotherNewItem.setName("Long screw");
        anotherNewItem.setType("SCREWS");
        app.createNewItem(anotherNewItem);
        app.withFlushModeCommit(newItem.getId(), anotherNewItem.getId());
        app.withFlushModeAuto(newItem.getId(), anotherNewItem.getId());

        app.createAndUpdateMake();

        app.emf.close();
    }
}
