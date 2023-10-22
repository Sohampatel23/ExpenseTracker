package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    TokenService tokenService;
}
