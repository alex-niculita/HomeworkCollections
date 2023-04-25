package com.example.hwcollections.models;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    //    Так как я вместо номеров отделов использовал Enum а в задании надо использовать номер, чтобы работать с отделами, то делаю конвертацию номера в Enum
    Employee.Departments convertIntToEnum(int x);

    //methods for list
    Employee addEmployee(String firstName, String lastName, Employee.Departments department, double salary);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    Map<String, Employee> getEmployees();
    void checkString(String firstName, String lastName);
}
