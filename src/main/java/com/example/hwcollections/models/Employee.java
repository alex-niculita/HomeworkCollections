package com.example.hwcollections.models;

public class Employee {
    private String firstName;
    private String lastName;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        if (this.firstName.equals(employee.firstName)) {
            return this.lastName.equals(employee.lastName);
        } else return false;
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() * lastName.hashCode();
    }

    @Override
    public String toString() {
        return String.format("{\"firstName\":\"" + firstName + "\",\"lastName\":\"" + lastName + "\"}");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
