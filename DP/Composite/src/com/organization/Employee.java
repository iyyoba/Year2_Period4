package com.organization;

public class Employee implements OrganizationComponent {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException("Employee cannot contain children");
    }

    @Override
    public void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException("Employee cannot contain children");
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void printXML(String indent) {
        System.out.println(indent + "<Employee name=\"" + name + "\" salary=\"" + salary + "\" />");
    }
}