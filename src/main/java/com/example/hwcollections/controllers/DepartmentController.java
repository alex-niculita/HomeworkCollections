package com.example.hwcollections.controllers;


import com.example.hwcollections.exceptions.MissingParametersException;
import com.example.hwcollections.models.Employee;
import com.example.hwcollections.models.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/departments/")
public class DepartmentController {

    private final EmployeeService employeeService;

    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("max-salary")
    public Employee maxSalary(@RequestParam int department) {

        return employeeService.findEmployeeWithMaxSalaryForDept(department);
    }

    @GetMapping("min-salary")
    public Employee minSalary(@RequestParam int department) {

        return employeeService.findEmployeeWithMinSalaryForDept(department);
    }

    @GetMapping(path = "all", params = "department")
    public List<Employee> allDept(@RequestParam int department) {

        return employeeService.getDepartment(department);
    }

    @GetMapping("all")
    public Map<Employee.Departments, List<Employee>> all() {
        return employeeService.getAllByDept();
    }

}
