package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void testCascadeRemoveAndOrphanRemoval() {
        // Create Department and Employees
        Department department = new Department();
        department.setName("Sales");

        Employee emp1 = new Employee();
        emp1.setName("Alice");

        Employee emp2 = new Employee();
        emp2.setName("Bob");

        // Add Employees to Department
        department.addEmployee(emp1);
        department.addEmployee(emp2);

        // Save Department (will also save Employees due to cascading)
        departmentRepository.save(department);

        // Remove Employee from Department (should trigger orphan removal)
        department.removeEmployee(emp1);

        // Delete Department (should delete all remaining Employees due to cascade remove)
        departmentRepository.delete(department);
    }
}
