package com.dev.Splitwise.service;

import com.dev.Splitwise.entity.User;

public interface UserService {
    User signup(String name , String email , String password);
    User login(String email , String password);

}
