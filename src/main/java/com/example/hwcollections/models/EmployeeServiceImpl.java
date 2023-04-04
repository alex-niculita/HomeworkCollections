package com.example.hwcollections.models;

import com.example.hwcollections.exceptions.EmployeeAlreadyAddedException;
import com.example.hwcollections.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{

//    private final List<Employee> employees = new ArrayList<>();
    private final Map<String,Employee> employees1 = new HashMap<>();

    // Testing
    {
        Employee employee = new Employee("Mike","Nam");
        employees1.put(employee.getId(),employee);
        Employee employee1 = new Employee("Alex","Wo");
        employees1.put(employee1.getId(),employee1);
    }
    // Methods for List

    @Override
    public Employee addEmployee(Employee employee) {
        try{
            if (findEmployee(employee.getFirstName(), employee.getLastName()).equals(employee) ) {
                throw new EmployeeAlreadyAddedException("Employee " + employee.getFirstName() + " "
                        + employee.getLastName() + " already added.");
            }
        }catch (EmployeeNotFoundException ignored) {

        }
        employees1.put(employee.getId(),employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(Employee employee) throws EmployeeNotFoundException {
        Employee employee1 = findEmployee(employee.getFirstName(), employee.getLastName());
        employees1.remove(employee1.getId());
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName,lastName);

        if(employees1.containsValue(employee)){
            return employee;
        }

//        for (int i = 0; i < employees.size(); i++) {
//            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)){
//                return employees.get(i);
//            }
//        }

        throw new EmployeeNotFoundException("Employee " + firstName + " " + lastName + " not found.");
    }

    public Map<String, Employee> getEmployees() {
        return employees1;
    }

    // Methods for Array

    // Find how many items are in the array if getSize == employees.length then array is full
    private int getSize (Employee[] employees){
        int counter = 0;
        if(employees==null) return -1;
        for(Employee employee:employees){
            if(employee!=null){
                counter++;
            }
        }
        return counter;
    }

    //    Добавить нового сотрудника в массив.
    //    Если массив полный то EmployeeStorageIsFullException.
    //    Если сотрудник уже в массиве то EmployeeAlreadyAddedException.
    //    Далее ищем свободную ячейку в массиве и добавляем нового сотрудника в нее.
//    @Override
//    public void addEmployeeToArray(Employee employee) {
//
//        if (employees.length == getSize(employees)){
//            throw new EmployeeStorageIsFullException("No space in array available for new employee");
//        }
//
//        for(int i=0;i<employees.length;i++){
//            if(employees[i]==employee){
//                throw new new EmployeeAlreadyAddedException("Employee " + employee.getFirstName() + " "
//                    + employee.getLastName() + " already added.");
//            }
//        }
//        for(int i=0;i<employees.length;i++){
//            if(employees[i]==null){
//                employees[i] = employee;
//            }
//        }
//    }
}
