package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.SignInInputDto;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User user)
    {
        return userService.userSignUp(user);
    }



    //sign in
    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable String email,@PathVariable String password)
    {
        return userService.userSignIn(email,password);
    }
}
