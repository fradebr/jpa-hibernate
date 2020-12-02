package eu.fade.wrh;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private void createTable() {
        EntityManagerFactory emf;
        EntityManager em;

        emf = Persistence
                .createEntityManagerFactory("warehouse");
        em = emf.createEntityManager();

    }

    public static void main(String[] args) {
        Main app = new Main();
        app.createTable();
    }
}
