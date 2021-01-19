package com.example.userManagement.service;

import com.example.userManagement.entity.userData;
import com.example.userManagement.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userInfoService {

    @Autowired
    private userRepository repository;          // injecting repository into userInfoService

    public userData saveUserData(userData userdata ) {
        return repository.save(userdata);       // save is inbuilt method given by jpa repos
    }


    public userData getuserDatabyID(int id){
        return repository.findById(id).orElse(null);
    }


    public String deleteUserbyID(int id){
        repository.deleteById(id);
    return "user removed!!";
    }

    public List<userData> findByEmailID(String emailid) { return repository.findByEmailID(emailid); }
    public List<userData> findbyUserName(String username) { return repository.findByUserName(username); }
    public List<userData> findbyMobileNumber(long mobilenumber) {return repository.findByMobileNumber(mobilenumber);}

}
