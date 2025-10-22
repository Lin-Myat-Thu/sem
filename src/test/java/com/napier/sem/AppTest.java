package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class AppTest {

    static App app;
    @BeforeAll
    static void init() {
        app = new App();
    }
    @Test
    void printSalariesTestNull() {
        app.printSalaries(null);
    }
    @Test
    void printSalariesTestEmpty()
    {
        ArrayList<Employee> employess = new ArrayList<Employee>();
        app.printSalaries(employess);
    }
    @Test
    void printSalariesTestContainsNull()
    {
        ArrayList<Employee> employess = new ArrayList<Employee>();
        employess.add(null);
        app.printSalaries(employess);
    }
    @Test
    void printSalaries()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        emp.title = "Engineer";
        emp.salary = 55000;
        employees.add(emp);
        app.printSalaries(employees);
    }

    @Test
    void testDisplayEmployee_NullEmployee() {
        app.displayEmployee(null);
    }
    @Test
    void testDisplayEmployee_AllDetailsPresent() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Department department = new Department();
        department.dept_no = "1";
        department.dept_name = "Engineer";

        Employee manger = new Employee();
        manger.emp_no = 2;
        manger.first_name = "Alice";
        manger.last_name = "Smith";
        manger.title = "Senior Manager";
        manger.salary = 85000;


        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "John";
        emp.last_name = "Doe";
        emp.title = "Engineer";
        emp.salary = 65000;
        emp.dept= department;
        emp.manager = manger;
        employees.add(emp);

        department.manager = manger;

        app.displayEmployee(emp);
    }
    @Test
    void testDisplayEmployee_NoManager(){
        ArrayList<Employee> employees = new ArrayList<Employee>();

        Department department = new Department();
        department.dept_no = "1";
        department.dept_name = "Engineer";

        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "John";
        emp.last_name = "Doe";
        emp.title = "Engineer";
        emp.salary = 65000;
        emp.dept= department;
        employees.add(emp);
        app.displayEmployee(emp);
    }


}