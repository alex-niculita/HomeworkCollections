package com.example.hwcollections.models;

import com.example.hwcollections.exceptions.NoDepartmentException;
import com.example.hwcollections.exceptions.NoEmployeesException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {

    private final EmployeeService employeeService;

    public DepartmentsServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee.Departments convertIntToEnum(int x) {

        return switch (x) {
            case 1 -> Employee.Departments.IT;
            case 2 -> Employee.Departments.FINANCE;
            case 3 -> Employee.Departments.HR;
            case 4 -> Employee.Departments.SALES;
            case 5 -> Employee.Departments.MARKETING;
            default -> null;
        };
    }

    @Override
    public List<Employee> getDepartment(int departmentN) {

        if(employeeService.getEmployees().isEmpty()){
            throw new NoEmployeesException("No Employees found.");
        }

        Employee.Departments department = convertIntToEnum(departmentN);

        if(department == null) {
            throw new NoDepartmentException("Department " + departmentN + " is not found.");
        }

        return employeeService.getEmployees().values().stream().filter(e->e.getDepartment().equals(department)).collect(Collectors.toList());
    }

    // Методы для работы с отделами а не со всем массивом

    @Override
    public Employee findEmployeeWithMinSalaryForDept(int department) {

        Employee.Departments d = convertIntToEnum(department);

        if(d == null) {
            throw new NoDepartmentException("Department " + department + " is not found.");
        }

        if(employeeService.getEmployees().isEmpty()){
            throw new NoEmployeesException("No Employees found.");
        }

        List<Employee> dept = getDepartment(department);


        return dept.stream().min(Employee::compareTo).orElse(null);

    }

    @Override
    public Employee findEmployeeWithMaxSalaryForDept(int department) {

        Employee.Departments d = convertIntToEnum(department);

        if(d == null) {
            throw new NoDepartmentException("Department " + department + " is not found.");
        }

        if(employeeService.getEmployees().isEmpty()){
            throw new NoEmployeesException("No Employees found.");
        }

        List<Employee> dept = getDepartment(department);

        return dept.stream().max(Employee::compareTo).orElse(null);


    }

    @Override
    public Map<Employee.Departments,List<Employee>> getAllByDept(){
        Map<Employee.Departments,List<Employee>> allByDept = new HashMap<>();

        Arrays.stream(Employee.Departments.values()).forEach(d->allByDept.put(d,employeeService.getEmployees().values().stream().filter(e->e.getDepartment().equals(d)).toList()));

        return allByDept;
    }
}
