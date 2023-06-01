package org.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Wrapper {
    List<Employee> employees;
    public Wrapper(){
        employees=new ArrayList<>();
    }

    @XmlElement
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "employees=" + employees +
                '}';
    }
}
