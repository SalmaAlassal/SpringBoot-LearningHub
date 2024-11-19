package com.example.demo.batch;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;


public class DBWriter implements ItemWriter<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(Chunk<? extends User> users) throws Exception {
        System.out.println("Data Saved for Users: " + users);
        userRepository.saveAll(users);
    }
}