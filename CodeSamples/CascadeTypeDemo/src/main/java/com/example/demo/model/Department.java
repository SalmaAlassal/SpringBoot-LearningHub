package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


//    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL) // CascadeType.ALL includes CascadeType.REMOVE
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);  // Necessary for orphan removal to work
    }

}
