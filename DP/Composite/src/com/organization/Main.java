package com.organization;

public class Main {

    public static void main(String[] args) {

        // Create employees
        Employee e1 = new Employee("Alice", 5000);
        Employee e2 = new Employee("Bob", 6000);
        Employee e3 = new Employee("Charlie", 7000);
        Employee e4 = new Employee("Diana", 8000);

        // Create departments
        Department devDept = new Department("Development");
        Department hrDept = new Department("HR");
        Department headDept = new Department("Head Office");

        // Build hierarchy
        devDept.add(e1);
        devDept.add(e2);

        hrDept.add(e3);

        headDept.add(devDept);
        headDept.add(hrDept);
        headDept.add(e4); // employee directly under root

        // Print total salary
        System.out.println("Total Salary of Organization: " + headDept.getSalary());

        System.out.println("\n--- Organization Structure (XML) ---");
        headDept.printXML("");
    }
}
