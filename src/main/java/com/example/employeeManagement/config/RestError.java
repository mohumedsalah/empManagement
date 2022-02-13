package com.example.employeeManagement.config;

import org.springframework.http.HttpStatus;

public interface RestError {

    String error();

    HttpStatus httpStatus();

    String description();

}
