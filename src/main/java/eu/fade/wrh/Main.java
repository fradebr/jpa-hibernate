package eu.fade.wrh;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

import eu.fade.wrh.domain.Address;
import eu.fade.wrh.domain.Branch;
import eu.fade.wrh.domain.BranchService;
import eu.fade.wrh.domain.Department;
import eu.fade.wrh.domain.Detail;
import eu.fade.wrh.domain.Employee;
import eu.fade.wrh.domain.EmployeeService;
import eu.fade.wrh.domain.Function;
import eu.fade.wrh.domain.Item;
import eu.fade.wrh.domain.ItemService;
import eu.fade.wrh.domain.Make;

public class Main {

    EntityManagerFactory emf;
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

    private void useEmployeeService() {
        EmployeeService service = new EmployeeService();
        Employee employee = new Employee();
        employee.setFirstName("Alain");
        employee.setLastName("Vandam");
        employee.setEmployeeNumber("088123");
        employee.setDateInService(LocalDate.of(2018, 11, 19));
        employee.setFunction(Function.EMPLOYEE);
        employee.setActive(true);
        employee.setWage(2000.0);

        Address address = new Address();
        address.setStreet("My street");
        address.setNumber("15");
        address.setZip("123456");
        address.setCity("My city");
        employee.setAddress(address);

        employee = service.createNewEmployee(employee);
        System.out.println(employee);
        Employee foundEmployee = service.getEmployeeById(employee.getId());
        System.out.println(foundEmployee);
        System.out.println("The seniority of " + employee.getFirstName() + " " + employee.getLastName() + " is " + employee.getSeniority() + " years");
    }

    private void createItemsAndMakes() {
        Item newItem = new Item();
        newItem.setId(2);
        newItem.setMake("Make");
        newItem.setCurrentStock(88);
        newItem.setName("Short screw");
        newItem.setType("SCREWS");

        createNewItem(newItem);
        Item foundItem = findItemById(newItem.getId());
        getReference(newItem.getId());
        removeItem(foundItem);
        findItemById(foundItem.getId());

        newItem = createNewItem(newItem);
        detach(newItem.getId());
        checkPersistenceContext(newItem.getId());

        Item anotherNewItem = new Item();
        anotherNewItem.setId(3);
        anotherNewItem.setMake("Make");
        anotherNewItem.setCurrentStock(50);
        anotherNewItem.setName("Long screw");
        anotherNewItem.setType("SCREWS");
        createNewItem(anotherNewItem);
        withFlushModeCommit(newItem.getId(), anotherNewItem.getId());
        withFlushModeAuto(newItem.getId(), anotherNewItem.getId());

        createAndUpdateMake();

        emf.close();
    }

    private void useBranchService() {
        BranchService service = new BranchService();

        Branch branch = new Branch();
        branch.setName("Branch 1");
        branch.setLocation("Location 1");

        Department dep1 = new Department();
        dep1.setName("DEP 1");
        dep1.setManager("Manager 1");

        Department dep2 = new Department();
        dep2.setName("DEP 2");
        dep2.setManager("Manager 2");

        Department dep3 = new Department();
        dep3.setName("DEP 3");
        dep3.setManager("Manager 2");

        branch.setDepartments(List.of(dep1, dep2, dep3));

        branch = service.addBranch(branch);
        System.out.println(branch);

        Branch branch2 = service.getBranchById(branch.getId());
        branch2.setLocation("Updated location");
        branch2 = service.updateBranch(branch2);
        System.out.println(branch2);

        Branch branch3 = service.getBranchById(branch.getId());
        service.deleteBranch(branch3);
        Branch branch4 = service.getBranchById(branch.getId());
        System.out.println(branch4);

    }

    private void useItemService() {
        ItemService service = new ItemService();

        Item item = new Item();
        item.setName("Item 1");
        item.setCurrentStock(20);
        item.setMake("Make 1");

        Detail detail = new Detail();
        detail.setMaximumStock(99);
        detail.setMinimumStock(5);
        detail.setPrice(12.0);

        item.addDetail(detail);
        service.createNewItem(item);

        Item item2 = service.getItemById(item.getId());
        System.out.println(item2);

        service.deleteItem(item);

        Item item3 = service.getItemById(item.getId());
        System.out.println(item3);
    }

    public static void main(String[] args) throws InterruptedException {
        Main app = new Main();
//        app.createItemsAndMakes();
//
//        Thread.sleep(2000);
//
//        System.out.println("_______________________________________________________________________________________________________");
//        System.out.println("_______________________________________________________________________________________________________");
//        app.useEmployeeService();

        Thread.sleep(2000);

        System.out.println("_______________________________________________________________________________________________________");
        System.out.println("_______________________________________________________________________________________________________");
//        app.useBranchService();
        app.useItemService();
    }
}
