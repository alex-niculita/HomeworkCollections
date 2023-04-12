package com.example.hwcollections.controllers;

import com.example.hwcollections.exceptions.*;
import com.example.hwcollections.models.Employee;
import com.example.hwcollections.models.EmployeeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.CommunicationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/employee/")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //добавить объект Employee и вывести его в формате JSON
    @GetMapping("add")
    public Employee addEmployee(@RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {

        if(firstName == null || lastName == null || firstName.isBlank() || lastName.isBlank()) {
            throw new MissingParametersException("Error! Something is missing");
        }

        Employee employee = new Employee(firstName, lastName);

        return employeeService.addEmployee(employee);
    }

//     Выводим ошибку если такой работник уже существует

//    @ExceptionHandler(EmployeeAlreadyAddedException.class)
//    public String handleExceptionAdd(EmployeeAlreadyAddedException e){
//        return e.getMessage();
//    }

    //удалить объект Employee и вывести его в формате JSON
    @GetMapping("remove")
    public Employee removeEmployee(@RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {

        if(firstName == null || lastName == null || firstName.isBlank() || lastName.isBlank()) {
            throw new MissingParametersException("Error! Something is missing");
        }

        Employee employee = new Employee(firstName, lastName);

        return employeeService.removeEmployee(employee);
    }

    // Выводим ошибку если такого работника не нашли
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleExceptionRemove(EmployeeNotFoundException e){
        return e.getMessage();
    }

    //найти объект Employee и вывести его в формате JSON
    @GetMapping("find")
    public Employee findEmployee(@RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName) {

        if(firstName == null || lastName == null || firstName.isBlank() || lastName.isBlank()) {
            throw new MissingParametersException("Error! Something is missing");
        }

        return employeeService.findEmployee(firstName, lastName);
    }

    // Выводим ошибку если паратетры отсутствуют
    @ExceptionHandler(MissingParametersException.class)
    public String handleExceptionParams(MissingParametersException e){
        return e.getMessage();
    }

    //список всех сотрудников в формате JSON
    @GetMapping("show")
    public Map<String, Employee> showAll(){
        return employeeService.getEmployees();
    }

}
