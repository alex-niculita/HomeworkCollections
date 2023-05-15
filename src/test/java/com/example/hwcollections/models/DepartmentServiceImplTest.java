package com.example.hwcollections.models;

import com.example.hwcollections.exceptions.NoDepartmentException;
import com.example.hwcollections.exceptions.NoEmployeesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    private List<Employee> employees;
    private Map<String,Employee> mapReturned;
    private Map<Integer,List<Employee>> mapExpected;

    @Mock
    private DepartmentsService departmentsServiceMock;
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    public void setup() {
        employees = new ArrayList<>();
        mapReturned = new HashMap<>();
        Employee employee = new Employee("Muhammed", "Abbas", Employee.Departments.SALES,100.00);
        employees.add(employee);
        mapReturned.put(employee.getId(),employee);
        employee = new Employee("Tim", "Johns", Employee.Departments.SALES,133.2);
        employees.add(employee);
        mapReturned.put(employee.getId(),employee);
        employee = new Employee("Carl", "Marks", Employee.Departments.SALES,2000.00);
        employees.add(employee);
        mapReturned.put(employee.getId(),employee);

        mapExpected = new HashMap<>();

        mapExpected.put(1,new ArrayList<>());
        mapExpected.put(2,new ArrayList<>());
        mapExpected.put(3,new ArrayList<>());
        mapExpected.put(4, employees);
        mapExpected.put(5,new ArrayList<>());

    }

    /**
     * __TESTS__ возвращает список сотрудников по департаменту.
     **/
    // valid department number
    @Test
    public void getEmployees_test1() {
        when(departmentsServiceMock.getDepartment(4)).thenReturn(employees);

        assertEquals(employees, out.getEmployees(4));
    }

    // invalid department number
    @Test
    public void getEmployees_test2(){
        when(departmentsServiceMock.getDepartment(11)).thenThrow(NoDepartmentException.class);

        assertThrows(NoDepartmentException.class, () -> out.getEmployees(11));
    }

    // empty department
    @Test
    public void getEmployees_test3() {
        when(departmentsServiceMock.getDepartment(1)).thenThrow(NoEmployeesException.class);

        assertThrows(NoEmployeesException.class, () -> out.getEmployees(1));
    }

    /**
     * __TESTS__ возвращает сумму зарплат по департаменту.
     **/

    // valid department number
    @Test
    public void getSalarySum_test1() {
        when(departmentsServiceMock.getDepartment(4)).thenReturn(employees);
        double sumExpected = employees.stream().mapToDouble(Employee::getSalary).sum();
        assertEquals(sumExpected, out.getSalarySum(4));
    }

    // invalid department number
    @Test
    public void getSalarySum_test2(){
        when(departmentsServiceMock.getDepartment(11)).thenThrow(NoDepartmentException.class);

        assertThrows(NoDepartmentException.class, () -> out.getSalarySum(11));
    }

    // empty department
    @Test
    public void getSalarySum_test3() {
        when(departmentsServiceMock.getDepartment(1)).thenThrow(NoEmployeesException.class);

        assertThrows(NoEmployeesException.class, () -> out.getSalarySum(1));
    }

    /**
     * __TESTS__ возвращает максимальную зарплату по департаменту.
     **/
    // valid department number
    @Test
    public void getMaxSalary_test1() {
        when(departmentsServiceMock.findEmployeeWithMaxSalaryForDept(4)).
                thenReturn(new Employee("Carl", "Marks", Employee.Departments.SALES,2000.00 ));

        Double maxExpected = employees.stream().mapToDouble(Employee::getSalary).max().getAsDouble();
        assertEquals(maxExpected, out.getMaxSalary(4));
    }

    // invalid department number
    @Test
    public void getMaxSalary_test2(){
        when(departmentsServiceMock.findEmployeeWithMaxSalaryForDept(11)).thenThrow(NoDepartmentException.class);

        assertThrows(NoDepartmentException.class, () -> out.getMaxSalary(11));
    }

    /**
     * __TESTS__ возвращает минимальную зарплату по департаменту.
     **/

    // valid department number
    @Test
    public void getMinSalary_test1() {
        when(departmentsServiceMock.findEmployeeWithMinSalaryForDept(4)).
                thenReturn(new Employee("Muhammed", "Abbas", Employee.Departments.SALES,100.00));

        Double minExpected = employees.stream().mapToDouble(Employee::getSalary).min().getAsDouble();
        assertEquals(minExpected, out.getMinSalary(4));
    }

    // invalid department number
    @Test
    public void getMinSalary_test2(){
        when(departmentsServiceMock.findEmployeeWithMinSalaryForDept(11)).thenThrow(NoDepartmentException.class);

        assertThrows(NoDepartmentException.class, () -> out.getMinSalary(11));
    }

    /**
     * __TESTS__ возвращает сотрудников, сгруппированых по отделам в виде Map<Integer,List<Employees>>, где ключ — это номер отдела, а значение — список сотрудников данного отдела.
     **/

    @Test
    public void getAll_test1() {
        when(employeeServiceMock.getEmployees()).thenReturn(mapReturned);
        assertEquals(mapExpected, out.getAll());
    }

}