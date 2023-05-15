package com.example.hwcollections.models;

import com.example.hwcollections.exceptions.NoEmployeesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hwcollections.models.Employee;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
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

        if(department == null) { // если такого отдела нет то выходим и возвращаем null
            throw new NoEmployeesException("No Employees found.");
        }

        return employeeService.getEmployees().values().stream().filter(e->e.getDepartment().equals(department)).collect(Collectors.toList());
    }

    // Методы для работы с отделами а не со всем массивом

    @Override
    public Employee findEmployeeWithMinSalaryForDept(int department) {

        if(employeeService.getEmployees().isEmpty()){
            throw new NoEmployeesException("No Employees found.");
        }

        List<Employee> dept = getDepartment(department);


        return dept.stream().min(Employee::compareTo).orElse(null);

    }

    @Override
    public Employee findEmployeeWithMaxSalaryForDept(int department) {

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
