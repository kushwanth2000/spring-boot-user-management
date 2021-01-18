package com.example.userManagement.service;

import com.example.userManagement.entity.userData;
import com.example.userManagement.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userInfoService {

    @Autowired
    private userRepository repository;          // injecting repository into userInfoService

    public userData saveUserData(userData userdata ) {
        return repository.save(userdata);
    }
}
