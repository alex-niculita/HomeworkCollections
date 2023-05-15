package com.example.hwcollections.models;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentsService departmentsService;
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(DepartmentsService departmentsService, EmployeeService employeeService) {
        this.departmentsService = departmentsService;
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployees(Integer id) {
        return departmentsService.getDepartment(id);
    }

    @Override
    public double getSalarySum(Integer id) {
        List<Employee> employeesTemp = departmentsService.getDepartment(id);
        return employeesTemp.stream().mapToDouble(Employee::getSalary).sum();
    }

    @Override
    public double getMaxSalary(Integer id) {
        Employee employee = departmentsService.findEmployeeWithMaxSalaryForDept(id);
        return employee.getSalary();
    }

    @Override
    public double getMinSalary(Integer id) {
        return departmentsService.findEmployeeWithMinSalaryForDept(id).getSalary();
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
        Map<Employee.Departments,List<Employee>> allByDept = new HashMap<>();

        Arrays.stream(Employee.Departments.values()).forEach(d->allByDept.put(d,employeeService.getEmployees().values().stream().filter(e->e.getDepartment().equals(d)).toList()));
        Map<Integer, List<Employee>> companyInts = new HashMap<>();

//        Convert enum Employee.Departments to Integer to comply with the task to get Map<Integer, List<Employee>>
        allByDept.keySet().stream().mapToInt(DepartmentServiceImpl::convertEnumToInt).forEach(i->companyInts.put(i,departmentsService.getDepartment(i)));

        return companyInts;
    }

    public static Integer convertEnumToInt(Employee.Departments enm) {

        return switch (enm) {
            case IT -> 1;
            case FINANCE -> 2;
            case HR -> 3;
            case SALES -> 4;
            case MARKETING -> 5;
        };
    }
}
