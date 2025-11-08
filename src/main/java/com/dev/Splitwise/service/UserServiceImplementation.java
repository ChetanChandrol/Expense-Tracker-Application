package com.dev.Splitwise.service;

import com.dev.Splitwise.entity.User;
import com.dev.Splitwise.exception.PassWordIncorrectException;
import com.dev.Splitwise.exception.UserDoesnotExistException;
import com.dev.Splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User signup(String name, String email, String password) {
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser != null){
            throw new RuntimeException("Email already exist");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));;
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        User savedUser = userRepository.findUserByEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (savedUser==null)
        {
            throw new UserDoesnotExistException("User does not Exist");
        }
        if (bCryptPasswordEncoder.matches(password,savedUser.getPassword()))
        {
            return savedUser;
        }
        else
        {
            throw new PassWordIncorrectException("Password is incorrect");
        }
    }
}
