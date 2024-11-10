package com.example.demo;

import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void run(String... args) throws Exception {
        departmentService.testCascadeRemoveAndOrphanRemoval();
    }
}