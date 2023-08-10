package com.example.demo.service;

import com.example.demo.dao.MyBookListDao;
import com.example.demo.model.Book;
import com.example.demo.model.MyBookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {

    @Autowired
    MyBookListDao myBookListDao;

    public List<MyBookList> getMyBookList() {
        return  myBookListDao.findAll();
    }

    public void saveMyBook(MyBookList myBookList) {
        myBookListDao.save(myBookList);
    }

    public void deleteMyBook(Long id) {
        myBookListDao.deleteById(id);
    }
}