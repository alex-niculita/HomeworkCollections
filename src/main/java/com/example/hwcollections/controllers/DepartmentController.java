package com.example.hwcollections.controllers;

import com.example.hwcollections.models.DepartmentService;
import com.example.hwcollections.models.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department/")
public class DepartmentController {

    // Bean
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

//    GET http://localhost:8080/department/{id}/employees
// — возвращает список сотрудников по департаменту.

    @GetMapping("{id}/employees")
    public List<Employee> getDepartmentEmployees (@PathVariable Integer id) {
        return departmentService.getEmployees(id);
    }

//GET http://localhost:8080/department/{id}/salary/sum
// — возвращает сумму зарплат по департаменту.

    @GetMapping("{id}/salary/sum")
    public double getDepartmentSalarySum(@PathVariable Integer id) {
        return departmentService.getSalarySum(id);
    }

//GET http://localhost:8080/department/{id}/salary/max
// — возвращает максимальную зарплату по департаменту.

    @GetMapping("{id}/salary/max")
    public double getDepartmentMaxSalary(@PathVariable Integer id) {
        return departmentService.getMaxSalary(id);
    }

//GET http://localhost:8080/department/{id}/salary/min
// — возвращает минимальную зарплату по департаменту.

    @GetMapping("{id}/salary/min")
    public double getDepartmentMinSalary(@PathVariable Integer id) {
        return departmentService.getMinSalary(id);
    }

//GET http://localhost:8080/department/employees
// — возвращает сотрудников, сгруппированых по отделам в виде Map<Integer,List<Employees>>, где ключ — это номер отдела, а значение — список сотрудников данного отдела.

    @GetMapping("employees")
    public Map<Integer,List<Employee>> getAllDepartments() {
        return departmentService.getAll();
    }

}
