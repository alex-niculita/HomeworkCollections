package com.example.hwcollections.controllers;

import com.example.hwcollections.exceptions.EmployeeAlreadyAddedException;
import com.example.hwcollections.exceptions.EmployeeNotFoundException;
import com.example.hwcollections.models.Employee;
import com.example.hwcollections.models.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //добавить объект Employee и вывести его в формате JSON или сообщение об ошибке
    @GetMapping("/employee/add")
    public String addEmployee(@RequestParam(required = false) String firstName,
                                @RequestParam(required = false) String lastName) {

        if(firstName.isBlank() || lastName.isBlank()) {
            return "Error! Something is missing!";
        }

        Employee employee = new Employee(firstName, lastName);

        try {
            employeeService.addEmployee(employee);
        } catch (EmployeeAlreadyAddedException e){
            return e.getMessage();
        } catch (EmployeeNotFoundException ignored) {

        }
        return employee.toString();
    }

    //удалить объект Employee и вывести его в формате JSON или сообщение об ошибке
    @GetMapping("/employee/remove")
    public String removeEmployee(@RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {

        if(firstName.isBlank() || lastName.isBlank()) {
            return "Error! Something is missing!";
        }

        Employee employee = new Employee(firstName, lastName);

        try {
            employeeService.removeEmployee(employee);
        } catch (EmployeeNotFoundException e){
            return e.getMessage();
        }

        return employee.toString();
    }

    //найти объект Employee и вывести его в формате JSON или сообщение об ошибке
    @GetMapping("/employee/find")
    public String findEmployee(@RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName) {

        if(firstName.isBlank() || lastName.isBlank()) {
            return "Error! Something is missing!";
        }

        Employee employee;
        try {
            employee = employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e){
            return e.getMessage();
        }

        return employee.toString();
    }


    //список всех сотрудников в формате JSON
    @GetMapping("/show")
    public List<Employee> showAll(){
        return employeeService.getEmployees();
    }

}
