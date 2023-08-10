package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.MyBookList;
import com.example.demo.service.BookService;
import com.example.demo.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MyBookListService myBookListService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register-book")
    public String bookRegister() {
        return "book-register";
    }

    @GetMapping("/available-books")
    public String availableBooks(Model model) {
        model.addAttribute("availableBooks", bookService.getAvailableBooks());
        return "available-books";
    }

    @GetMapping("/my-books")
    public String myBooks(Model model) {
        model.addAttribute("myBooks", myBookListService.getMyBookList());
        return "my-books";
    }

    @PostMapping("/save-book")
    public String saveBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/available-books";
    }

    @GetMapping("/edit-book/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "edit-book";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/available-books";
    }

    @GetMapping("/mylist/{id}")
    public String addBookToMyList(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        MyBookList myBookList = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
        myBookListService.saveMyBook(myBookList);
        bookService.deleteBook(id);
        return "redirect:/my-books";
    }
}