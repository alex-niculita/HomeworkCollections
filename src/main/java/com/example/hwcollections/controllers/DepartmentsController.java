package com.example.hwcollections.controllers;


import com.example.hwcollections.models.DepartmentsService;
import com.example.hwcollections.models.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments/")
public class DepartmentsController {

    private final DepartmentsService departmentsService;

        public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }
    @GetMapping("max-salary")
    public Employee maxSalary(@RequestParam int department) {

        return departmentsService.findEmployeeWithMaxSalaryForDept(department);
    }

    @GetMapping("min-salary")
    public Employee minSalary(@RequestParam int department) {

        return departmentsService.findEmployeeWithMinSalaryForDept(department);
    }

    @GetMapping(path = "all", params = "department")
    public List<Employee> allDept(@RequestParam int department) {

        return departmentsService.getDepartment(department);
    }

    @GetMapping("all")
    public Map<Employee.Departments, List<Employee>> all() {
        return departmentsService.getAllByDept();
    }

}
