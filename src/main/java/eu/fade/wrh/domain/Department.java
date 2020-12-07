package eu.fade.wrh.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String manager;
    @ManyToMany(mappedBy = "departments", cascade = {CascadeType.ALL})
    private Set<Employee> employees = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(final String manager) {
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(final Set<Employee> employees) {
        this.employees = employees;
    }

    public Department addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.addDepartment(this);

        return this;
    }

    public Department removeEmployee(Employee employee) {
        if(this.employees != null) {
            this.employees.remove(employee);
            employee.removeDepartment(this);
        }
        return this;
    }
}
