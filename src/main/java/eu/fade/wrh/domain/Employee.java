package eu.fade.wrh.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;
    @Column(name="first_name", columnDefinition = "VARCHAR(40)")
    private String firstName;
    @Column(name="last_name", columnDefinition = "VARCHAR(60)", nullable = false)
    private String lastName;
    @Column(name="employee_number", columnDefinition = "VARCHAR(15)", unique = true)
    private String employeeNumber;
    @Column(name="date_in_service", nullable = false)
    private LocalDate dateInService;
    private boolean active;
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] picture;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50)")
    private Function function;
    @Column(nullable = false)
    private double wage;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(final String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public LocalDate getDateInService() {
        return dateInService;
    }

    public void setDateInService(final LocalDate dateInService) {
        this.dateInService = dateInService;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(final byte[] picture) {
        this.picture = picture;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(final Function function) {
        this.function = function;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(final double wage) {
        this.wage = wage;
    }

    public int getSeniority() {
        return (int)ChronoUnit.YEARS.between(dateInService, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", dateInService=" + dateInService +
                ", active=" + active +
                ", picture=" + Arrays.toString(picture) +
                ", function=" + function +
                ", wage=" + wage +
                '}';
    }
}
