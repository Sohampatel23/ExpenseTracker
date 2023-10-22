package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.UserToken;
import com.example.ExpenseTracker.repo.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    ITokenRepo iTokenRepo;

    public void createToken(UserToken token) {
        iTokenRepo.save(token);
    }

    public boolean authenticate(String email,String tokenValue) {


        //find thr actual token -> get the connected patient -> get its email-> verify with passed email

        //return ipTokenRepo.findFirstByTokenValue(tokenValue).getPatient().getPatientEmail().equals(email);

        UserToken token =  iTokenRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getUser().getUserEmail().equals(email);
        }
        else
        {
            return false;
        }
    }
}
