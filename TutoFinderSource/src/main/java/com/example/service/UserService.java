package com.example.service;

import com.example.entity.UserSignIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserSignIn> getAllUsers(Pageable pageable);
    public UserSignIn registerUser(UserSignIn user);
}
