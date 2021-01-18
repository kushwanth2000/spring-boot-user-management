package com.example.userManagement.controller;


import com.example.userManagement.entity.userData;
import com.example.userManagement.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {
    @Autowired
    private userInfoService service;


    @PostMapping("/adduser")
    public userData addUser(@RequestBody userData userdata)
    {
       return service.saveUserData(userdata);
    }

    @GetMapping("/userbyID/{id}")
    public userData findUserbyID(@PathVariable int id)
    {
        return service.getuserDatabyID(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id)
    {
        return service.deleteUserbyID(id);
    }

}
