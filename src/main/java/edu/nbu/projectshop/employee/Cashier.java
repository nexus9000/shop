package edu.nbu.projectshop.employee;

import java.util.Objects;

public class Cashier implements Employee {
    private final int id;
    private final String firstName;
    private final String lastName;
    private double salary;

    public Cashier(int id, String firstName, String lastName, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Override
    public String getNames() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Double getSalary() {
        return salary;
    }

    @Override
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cashier cashier = (Cashier) o;

        if (id != cashier.id) return false;
        if (!firstName.equals(cashier.firstName)) return false;
        return lastName.equals(cashier.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "id " + this.getId() + " " + this.getNames();
    }
}
