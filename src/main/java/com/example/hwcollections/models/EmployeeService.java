package com.example.hwcollections.models;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    //methods for list
    Employee addEmployee(Employee employee);
    Employee removeEmployee(Employee employee);
    Employee findEmployee(String firstName, String lastName);
    Map<String, Employee> getEmployees();
    // methods for array
//    void addEmployeeToArray(Employee employee);
}
