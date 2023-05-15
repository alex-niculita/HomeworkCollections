package com.example.hwcollections.models;

import java.util.List;
import java.util.Map;

public interface DepartmentsService {
    List<Employee> getDepartment(int departmentN);
    Employee findEmployeeWithMinSalaryForDept(int department);
    Employee findEmployeeWithMaxSalaryForDept(int department);
    Map<Employee.Departments,List<Employee>> getAllByDept();

}
