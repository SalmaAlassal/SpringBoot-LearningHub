package com.example.demo.batch;

import com.example.demo.model.User;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashMap;
import java.util.Map;

public class Processor implements ItemProcessor<User, User> {
    private static final Map<String, String> DEPT_NAMES = new HashMap<>();

    public Processor() {
        DEPT_NAMES.put("001", "Technology");
        DEPT_NAMES.put("002", "Operations");
        DEPT_NAMES.put("003", "Accounts");
    }

    @Override
    public User process(User user) throws Exception {
        String deptCode = user.getDept();
        String dept = DEPT_NAMES.get(deptCode);
        user.setDept(dept);
        System.out.println(String.format("Converted from [%s] to [%s]", deptCode, dept));
        return user;
    }
}