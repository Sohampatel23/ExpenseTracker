package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.SignInInputDto;
import com.example.ExpenseTracker.model.User;
import com.example.ExpenseTracker.model.UserToken;
import com.example.ExpenseTracker.repo.IUserRepo;
import com.example.ExpenseTracker.service.emailUtility.EmailHandler;
import com.example.ExpenseTracker.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    TokenService tokenService;

    public String userSignUp(User user) {

        String newEmail = user.getUserEmail();

        User existingUSer = iUserRepo.findFirstByUserEmail(newEmail);

        if(existingUSer != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = user.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            user.setUserPassword(encryptedPassword);


            // patient table - save patient
            iUserRepo.save(user);
            return "patient registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignIn(String email, String password) {
        User existingUser = iUserRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                UserToken token  = new UserToken(existingUser);

                if(EmailHandler.sendEmail(email,"otp after login", token.getTokenValue())) {
                    tokenService.createToken(token);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }
}
