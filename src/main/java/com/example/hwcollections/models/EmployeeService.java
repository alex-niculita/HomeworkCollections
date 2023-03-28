package com.example.hwcollections.models;

import java.util.List;

public interface EmployeeService {
    //methods for list
    void addEmployee(Employee employee);
    void removeEmployee(Employee employee);
    Employee findEmployee(String firstName, String lastName);
    List<Employee> getEmployees();
    // methods for array
//    void addEmployeeToArray(Employee employee);
}
