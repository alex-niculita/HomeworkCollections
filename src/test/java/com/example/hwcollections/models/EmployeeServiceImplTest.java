package com.example.hwcollections.models;

import com.example.hwcollections.exceptions.EmployeeAlreadyAddedException;
import com.example.hwcollections.exceptions.EmployeeNotFoundException;
import com.example.hwcollections.exceptions.WrongEntryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeService out;

    @BeforeEach
    public void setup() {
        out = new EmployeeServiceImpl();
    }

    /**
     * __ADD EMPLOYEE TESTS__
     **/

    // add new employee
    @Test
    public void addEmployee_test1() {
        Employee employeeTested = out.addEmployee("Hello", "Bye", Employee.Departments.SALES, 11223.09);
        Employee expectedEmployee = new Employee("Hello", "Bye", Employee.Departments.SALES, 11223.09);
        assertNotNull(employeeTested);
        assertEquals(expectedEmployee, employeeTested);
    }

    // add existing employee
    @Test
    public void addEmployee_test2() {
        assertThrows(EmployeeAlreadyAddedException.class, ()->out.addEmployee("Sarah", "Thompson", Employee.Departments.IT,1505.35));
    }

    // add new employee - input with invalid characters
    @Test
    public void addEmployee_test3() {
        assertThrows(WrongEntryException.class, ()->out.addEmployee("Sar2ah", "Thompson", Employee.Departments.IT,1505.35));
    }

    /**
     * __DELETE EMPLOYEE TESTS__
     **/

    // delete existing employee
    @Test
    public void deleteEmployee_test1() {
        Employee employeeTested = out.removeEmployee("Carl", "Marks");
        Employee expectedEmployee = new Employee("Carl", "Marks", Employee.Departments.SALES,2000.00);
        assertNotNull(employeeTested);
        assertEquals(expectedEmployee, employeeTested);
    }

    // delete non-existing employee
    @Test
    public void deleteEmployee_test2() {
        assertThrows(EmployeeNotFoundException.class, ()->out.removeEmployee("Hello", "Bye"));
    }

    // delete employee - input with invalid characters
    @Test
    public void deleteEmployee_test3() {
        assertThrows(WrongEntryException.class, ()->out.removeEmployee("Hel_lo", "By?e"));
    }

    /**
     * __SEARCH EMPLOYEE TESTS__
     **/

    //search existing employee
    @Test
    public void findEmployee_test1(){
        Employee employeeTested = out.findEmployee("Tim", "Johns");
        Employee expectedEmployee = new Employee("Tim", "Johns", Employee.Departments.SALES,133.2);
        assertNotNull(employeeTested);
        assertEquals(expectedEmployee, employeeTested);
    }

    // search non-existing employee
    @Test
    public void findEmployee_test2(){
        assertThrows(EmployeeNotFoundException.class, ()->out.findEmployee("Hello", "Bye"));
    }

}