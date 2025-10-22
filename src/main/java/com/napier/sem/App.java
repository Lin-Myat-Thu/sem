package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        if(args.length < 1){
            a.connect("localhost:33060",3000);
        }else {
            a.connect(args[0],Integer.parseInt(args[1]));
        }


        //Get Salaries by department
        Department salesDept = a.getDepartment("Sales");
        //Print salaries by department
        a.displayDepartmentSalaries(salesDept);

        // Get Employee info by thier first and last name
        Employee emp = a.getEmployeeByName("Maja","Lamba");
        //Print Employee info
        a.displayEmployee(emp);

        // Extract employee salary information
//        ArrayList<Employee> employees = a.getAllSalaries();

        // Test the size of the returned data - should be 240124
//        System.out.println(employees.size());

        //Display Employee Salaries
//        a.printSalaries(employees);



        // Disconnect from database
        a.disconnect();
    }
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location,int delay){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }
        int retries = 10;
        for (int i = 0; i < retries; i++) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" +
                        location+"/employees?useSSL=false&allowPublicKeyRetrieval=true", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException e){
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(e.getMessage());
            }
            catch (InterruptedException e){
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect(){
        if(con != null){
            try {
                con.close();
            }
            catch (SQLException e){
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Employee getEmployee(int emp_no) {
        try {
            String query = "SELECT e.emp_no, e.first_name, e.last_name, t.title, s.salary, d.dept_no, d.dept_name, " +
                    "mgr.emp_no AS mgr_no, mgr.first_name AS mgr_first, mgr.last_name AS mgr_last " +
                    "FROM employees e " +
                    "JOIN titles t ON e.emp_no = t.emp_no AND t.to_date = '9999-01-01' " +
                    "JOIN salaries s ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' " +
                    "JOIN dept_emp de ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' " +
                    "JOIN departments d ON de.dept_no = d.dept_no " +
                    "JOIN dept_manager dm ON d.dept_no = dm.dept_no AND dm.to_date = '9999-01-01' " +
                    "JOIN employees mgr ON dm.emp_no = mgr.emp_no " +
                    "WHERE e.emp_no = ?";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, emp_no);
            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("title");
                emp.salary = rset.getInt("salary");

                Department dept = new Department();
                dept.dept_no = rset.getString("dept_no");
                dept.dept_name = rset.getString("dept_name");
                emp.dept = dept;

                // Create manager Employee
                Employee manager = new Employee();
                manager.emp_no = rset.getInt("mgr_no");
                manager.first_name = rset.getString("mgr_first");
                manager.last_name = rset.getString("mgr_last");

                emp.manager = manager; // assign manager as Employee object

                return emp;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error getting employee: " + e);
            return null;
        }
    }


//    /**
//     * Gets all the current employees and salaries.
//     * @return A list of all employees and salaries, or null if there is an error.
//     */
//    public ArrayList<Employee> getAllSalaries(){
//        try {
//            //Create SQL statement
//            Statement stmt = con.createStatement();
//            // Create string for SQL statement
//            String query ="SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary\n" +
//                    "FROM employees, salaries\n" +
//                    "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01'\n" +
//                    "ORDER BY employees.emp_no ASC";
//            // Execute SQL statement
//            ResultSet rset = stmt.executeQuery(query);
//            // Extract employee information
//            ArrayList<Employee> employees = new ArrayList<Employee>();
//            while (rset.next()){
//                Employee emp = new Employee();
//                emp.emp_no = rset.getInt("employees.emp_no");
//                emp.first_name = rset.getString("employees.first_name");
//                emp.last_name = rset.getString("employees.last_name");
//                emp.salary = rset.getInt("salaries.salary");
//                employees.add(emp);
//            }
//            return employees;
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//            System.out.println("Failed to get salary details");
//            return null;
//        }
//    }

    public Department getDepartment(String dept_name) {
        try {
            String query = "SELECT d.dept_no, d.dept_name, e.emp_no, e.first_name, e.last_name " +
                    "FROM departments d " +
                    "JOIN dept_manager dm ON d.dept_no = dm.dept_no " +
                    "JOIN employees e ON dm.emp_no = e.emp_no " +
                    "WHERE d.dept_name = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, dept_name);
            ResultSet rset = stmt.executeQuery();

            if (rset.next()) {
                Department dept = new Department();
                dept.dept_no = rset.getString("dept_no");
                dept.dept_name = rset.getString("dept_name");

                Employee manager = new Employee();
                manager.emp_no = rset.getInt("emp_no");
                manager.first_name = rset.getString("first_name");
                manager.last_name = rset.getString("last_name");

                dept.manager = manager; // store manager
                return dept;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error getting department: " + e);
            return null;
        }
    }
    public ArrayList<Employee> getSalariesByDepartment(Department dept) {
        try {
            String query = "SELECT e.emp_no, e.first_name, e.last_name, s.salary " +
                    "FROM employees e " +
                    "JOIN dept_emp de ON e.emp_no = de.emp_no " +
                    "JOIN salaries s ON e.emp_no = s.emp_no " +
                    "WHERE de.dept_no = ? AND s.to_date = '9999-01-01' " +
                    "ORDER BY e.emp_no ASC";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, dept.dept_no);
            ResultSet rset = stmt.executeQuery();

            ArrayList<Employee> employees = new ArrayList<>();
            while (rset.next()) {
                Employee e = new Employee();
                e.emp_no = rset.getInt("emp_no");
                e.first_name = rset.getString("first_name");
                e.last_name = rset.getString("last_name");
                e.salary = rset.getInt("salary");
                employees.add(e);
            }
            return employees;
        } catch (Exception e) {
            System.out.println("Error getting salaries by department: " + e);
            return null;
        }
    }

    public Employee getEmployeeByName(String firstName, String lastName) {
        try {
            String query = "SELECT e.emp_no, e.first_name, e.last_name, t.title, s.salary, " +
                    "d.dept_no, d.dept_name, " +
                    "mgr.emp_no AS mgr_no, mgr.first_name AS mgr_first, mgr.last_name AS mgr_last " +
                    "FROM employees e " +
                    "JOIN titles t ON e.emp_no = t.emp_no AND t.to_date = '9999-01-01' " +
                    "JOIN salaries s ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01' " +
                    "JOIN dept_emp de ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01' " +
                    "JOIN departments d ON de.dept_no = d.dept_no " +
                    "JOIN dept_manager dm ON d.dept_no = dm.dept_no AND dm.to_date = '9999-01-01' " +
                    "JOIN employees mgr ON dm.emp_no = mgr.emp_no " +
                    "WHERE e.first_name = ? AND e.last_name = ?";

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            ResultSet rset = stmt.executeQuery();

            if(rset.next()){
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("title");
                emp.salary = rset.getInt("salary");

                Department dept = new Department();
                dept.dept_no = rset.getString("dept_no");
                dept.dept_name = rset.getString("dept_name");
                emp.dept = dept;

                // Create manager as an Employee object
                Employee manager = new Employee();
                manager.emp_no = rset.getInt("mgr_no");
                manager.first_name = rset.getString("mgr_first");
                manager.last_name = rset.getString("mgr_last");

                emp.manager = manager;

                return emp;
            } else {
                return null;
            }
        } catch(Exception e) {
            System.out.println("Error getting employee by name: " + e);
            return null;
        }
    }


    public void displayEmployee(Employee emp) {
        if (emp == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Employee Details:");
        System.out.println("-----------------");
        System.out.println("Employee ID  : " + emp.emp_no);
        System.out.println("First Name   : " + emp.first_name);
        System.out.println("Last Name    : " + emp.last_name);
        System.out.println("Title        : " + emp.title);
        System.out.println("Salary       : " + emp.salary);

        if (emp.dept != null) {
            System.out.println("Department   : " + emp.dept.dept_name + " (" + emp.dept.dept_no + ")");
        } else {
            System.out.println("Department   : N/A");
        }

        if (emp.manager != null) {
            System.out.println("Manager      : " + emp.manager.first_name + " " + emp.manager.last_name +
                    " (ID: " + emp.manager.emp_no + ")");
        } else {
            System.out.println("Manager      : N/A");
        }

        System.out.println();
    }

    /**
     * Prints a list of employees.
     * @param employees The list of employees to print.
     */
    public void printSalaries(ArrayList<Employee> employees)
    {
        //Check Employee is not null
        if(employees == null){
            System.out.printf("No employees found.");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the list
        for (Employee emp : employees)
        {
            if(emp == null){
                continue;
            }
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
            System.out.println(emp_string);
        }
    }


    public void displayDepartmentSalaries(Department dept) {
        if (dept == null) {
            System.out.println("Department not found.");
            return;
        }

        System.out.println("Department: " + dept.dept_name);
        System.out.println("Manager: " + dept.manager.first_name + " " + dept.manager.last_name);
        System.out.printf("%-10s %-15s %-15s %-10s\n", "EmpNo", "First Name", "Last Name", "Salary");

        ArrayList<Employee> empList = getSalariesByDepartment(dept);
        if (empList != null && !empList.isEmpty()) {
            for (Employee e : empList) {
                System.out.printf("%-10d %-15s %-15s %-10d\n",
                        e.emp_no, e.first_name, e.last_name, e.salary);
            }
        } else {
            System.out.println("No employees found in this department.");
        }
    }








}