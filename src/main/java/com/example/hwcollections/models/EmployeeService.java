package com.example.hwcollections.models;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    //    Так как я вместо номеров отделов использовал Enum а в задании надо использовать номер, чтобы работать с отделами, то делаю конвертацию номера в Enum
    Employee.Departments convertIntToEnum(int x);

    //methods for list
    Employee addEmployee(Employee employee);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    Map<String, Employee> getEmployees();

    // Для того чтобы работать с каждым отделом по отдельности будем работников отдела записывать в отдельный массив и его возвращать этим методом
    List<Employee> getDepartment(int departmentN);

    Employee findEmployeeWithMinSalaryForDept(int department);

    Employee findEmployeeWithMaxSalaryForDept(int department);

    Map<Employee.Departments,List<Employee>> getAllByDept();
    // methods for array
//    void addEmployeeToArray(Employee employee);
}
