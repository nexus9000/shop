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
    public String toString() {
        return "id " + this.getId() + " " + this.getNames();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cashier cashier)) return false;

        if (id != cashier.id) return false;
        if (Double.compare(cashier.salary, salary) != 0) return false;
        if (!Objects.equals(firstName, cashier.firstName)) return false;
        return Objects.equals(lastName, cashier.lastName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
