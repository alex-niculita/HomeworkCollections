package com.example.hwcollections.models;

public class Employee implements Comparable<Employee>{
//    private static int counter;
//
//    private Integer id;
    private String firstName;
    private String lastName;
    private String id;
    private Departments department;
    private double salary;

    public enum Departments{
        IT,
        FINANCE,
        HR,
        SALES,
        MARKETING
    }


    public Employee(String firstName, String lastName, Departments department, double salary) {
//        this.id = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = firstName+lastName;
        this.department = department;
        this.salary = salary;
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
        return String.format("{\"firstName\":\"" + firstName + "\",\"lastName\":\"" + lastName + "\"department\":\"" + department + "\",\"salary\":\"" + salary + "\"}");
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

    public String getId() {
        return id;
    }

    public Departments getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee employee) {
        if(this.getSalary()<employee.getSalary()){
            return -1;
        } else if(this.getSalary()>employee.getSalary()){
            return 1;
        }
        return 0;
    }

}
