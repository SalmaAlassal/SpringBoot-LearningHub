package com.example.demo.dao;

import com.example.demo.model.MyBookList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBookListDao extends JpaRepository<MyBookList, Long> {
}
