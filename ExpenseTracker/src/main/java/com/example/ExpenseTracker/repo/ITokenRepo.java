package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<UserToken,Integer> {

    UserToken findFirstByTokenValue(String authTokenValue);

    UserToken findFirstByUser(User user);
}
