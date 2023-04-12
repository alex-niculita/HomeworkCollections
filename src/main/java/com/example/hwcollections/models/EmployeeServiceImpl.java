package com.example.hwcollections.models;

import com.example.hwcollections.exceptions.EmployeeAlreadyAddedException;
import com.example.hwcollections.exceptions.EmployeeNotFoundException;
import com.example.hwcollections.exceptions.NoEmployeesException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{

//    private final List<Employee> employees = new ArrayList<>();
    private final Map<String,Employee> employees1 = new HashMap<>();

    // Test values
    {
        Employee employee = new Employee("Mark","Adams", Employee.Departments.FINANCE,1200.05);
        employees1.put(employee.getId(),employee);
        employee = new Employee("Sarah", "Thompson", Employee.Departments.IT,1505.35);
        employees1.put(employee.getId(),employee);
        employee = new Employee("Tim", "Johns", Employee.Departments.SALES,133.2);
        employees1.put(employee.getId(),employee);
        employee = new Employee("Muhammed", "Abbas", Employee.Departments.SALES,100.00);
        employees1.put(employee.getId(),employee);
        employee = new Employee("Carl", "Marks", Employee.Departments.SALES,2000.00);
        employees1.put(employee.getId(),employee);
        employee = new Employee("Bruce", "Lee", Employee.Departments.FINANCE,2005.00);
        employees1.put(employee.getId(),employee);
        employee = new Employee("Andrew", "Lincoln", Employee.Departments.IT,3000.00);
        employees1.put(employee.getId(),employee);
        employee = new Employee("Norman", "Reedus", Employee.Departments.IT,4000.00);
        employees1.put(employee.getId(),employee);


    }
    // Methods for List

    //    Так как я вместо номеров отделов использовал Enum а в задании надо использовать номер, чтобы работать с отделами, то делаю конвертацию номера в Enum
    @Override
    public Employee.Departments convertIntToEnum(int x) {
        switch (x) {
            case 1: return Employee.Departments.IT;
            case 2: return Employee.Departments.FINANCE;
            case 3: return Employee.Departments.HR;
            case 4: return Employee.Departments.SALES;
            case 5: return Employee.Departments.MARKETING;
            default: return null;
        }
    }

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
    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee1 = findEmployee(firstName, lastName);
        employees1.remove(employee1.getId());
        return employee1;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        if(employees1.containsKey(firstName + lastName)){
            return employees1.get(firstName+lastName);
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

    // Methods for Departments

    // Для того чтобы работать с каждым отделом по отдельности будем работников отдела записывать в отдельный массив и его возвращать этим методом
    @Override
    public List<Employee> getDepartment(int departmentN) {

        if(employees1.isEmpty()){
            throw new NoEmployeesException("No Employees found.");
        }

        Employee.Departments department = convertIntToEnum(departmentN);

        if(department == null) { // если такого отдела нет то выходим и возвращаем null
            throw new NoEmployeesException("No Employees found.");
        }

        List<Employee> employeesInDepartment = new ArrayList<>();
        for(Employee e: employees1.values()){
            if (e.getDepartment().equals(department)){
                employeesInDepartment.add(e);
            }
        }

        return employeesInDepartment;
    }

    // Методы для работы с отделами а не со всем массивом


    @Override
    public Employee findEmployeeWithMinSalaryForDept(int department) {

        if(employees1.isEmpty()){
            throw new NoEmployeesException("No Employees found.");
        }

        List<Employee> dept = getDepartment(department);

        Comparator<Employee> salaryCompare = (employee1, employee2) -> {
            if(employee1.getSalary()<employee2.getSalary()){
                return -1;
            } else if(employee1.getSalary()>employee2.getSalary()){
                return 1;
            }
            return 0;
        };
        return dept.stream().min(salaryCompare).orElse(null);

    }

    @Override
    public Employee findEmployeeWithMaxSalaryForDept(int department) {

        if(employees1.isEmpty()){
            throw new NoEmployeesException("No Employees found.");
        }

        List<Employee> dept = getDepartment(department);

        Comparator<Employee> salaryCompare = (employee1,employee2) -> {
            if(employee1.getSalary()<employee2.getSalary()){
                return -1;
            } else if(employee1.getSalary()>employee2.getSalary()){
                return 1;
            }
            return 0;
        };
        return dept.stream().max(salaryCompare).orElse(null);


    }

    @Override
    public String getAllByDept(){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 6; i++) {
            sb.append("Department: ").append(convertIntToEnum(i)).append("<br>");

            List<Employee> tempList = getDepartment(i);
            tempList.forEach(e->sb.append(e.toString()).append("<br>"));
        }

        return sb.toString();
    }

    // Methods for Array, No longer in use! We use Maps now

    // Find how many items are in the array if getSize == employees.length then array is full
//    private int getSize (Employee[] employees){
//        int counter = 0;
//        if(employees==null) return -1;
//        for(Employee employee:employees){
//            if(employee!=null){
//                counter++;
//            }
//        }
//        return counter;
//    }

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
