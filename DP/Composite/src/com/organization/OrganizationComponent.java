package com.organization;

public interface OrganizationComponent {

    void add(OrganizationComponent component);
    void remove(OrganizationComponent component);

    double getSalary();

    void printXML(String indent);
}