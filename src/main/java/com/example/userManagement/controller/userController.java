package com.example.userManagement.controller;


import com.example.userManagement.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
    @Autowired
    private userInfoService service;
    
}
