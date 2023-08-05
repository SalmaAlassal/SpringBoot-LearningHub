package com.example.demo.model;

import lombok.Data;

// @Data is comes from lombok, it is used to generate getter and setter methods
@Data
public class Response {
    private Integer id;
    private String response;
}