package com.example.demo.controller;

import com.example.demo.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyBookListController {

    @Autowired
    MyBookListService myBookListService;

    @GetMapping("/delete-list/{id}")
    public String deleteMyList(@PathVariable Long id) {
        myBookListService.deleteMyBook(id);
        return "redirect:/my-books";
    }
}