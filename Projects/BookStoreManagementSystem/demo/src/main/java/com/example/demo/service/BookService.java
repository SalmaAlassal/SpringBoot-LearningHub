package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public List<Book> getAvailableBooks() {
        return bookDao.findAll();
    }

    public Book getBookById(Long id) {
        return bookDao.findById(id).get();
    }

    public void saveBook(Book book) {
        bookDao.save(book);
    }

    public void deleteBook(Long id) {
        bookDao.deleteById(id);
    }
}