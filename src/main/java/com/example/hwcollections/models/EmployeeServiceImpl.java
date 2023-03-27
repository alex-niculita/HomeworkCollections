package com.example.hwcollections.models;

import com.example.hwcollections.exceptions.EmployeeAlreadyAddedException;
import com.example.hwcollections.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final List<Employee> employees = new ArrayList<>();

    {
        employees.add(new Employee("Mike","Nam"));
        employees.add(new Employee("Alex","Wo"));
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
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(Employee employee) throws EmployeeNotFoundException {
        employees.remove(findEmployee(employee.getFirstName(), employee.getLastName()));
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)){
                return employees.get(i);
            }
        }

        throw new EmployeeNotFoundException("Employee " + firstName + " " + lastName + " not found.");
    }

    public List<Employee> getEmployees() {
        return employees;
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
