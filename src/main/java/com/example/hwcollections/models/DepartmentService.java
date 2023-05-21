package com.example.hwcollections.models;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Employee> getEmployees(Integer id);
    double getSalarySum(Integer id);
    double getMaxSalary(Integer id);
    double getMinSalary(Integer id);
    Map<Integer,List<Employee>> getAll();


}
