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
        Map<Integer,List<Employee>> allByDept = new HashMap<>();

        // I use enum as a department. This Map helps convert enum Employee.Departments to Integer to comply with the task to get Map<Integer, List<Employee>>
        Map<Employee.Departments,Integer> convertEnumToInt = new HashMap<>();
        convertEnumToInt.put(Employee.Departments.IT, 1);
        convertEnumToInt.put(Employee.Departments.FINANCE, 2);
        convertEnumToInt.put(Employee.Departments.HR, 3);
        convertEnumToInt.put(Employee.Departments.SALES, 4);
        convertEnumToInt.put(Employee.Departments.MARKETING, 5);

        Arrays.stream(Employee.Departments.values()).forEach(d->allByDept.put(convertEnumToInt.get(d),employeeService.getEmployees().values().stream().filter(e->e.getDepartment().equals(d)).toList()));

        return allByDept;
    }

}
