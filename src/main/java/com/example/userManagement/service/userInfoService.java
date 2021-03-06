package com.example.userManagement.service;

import com.example.userManagement.entity.userData;
import com.example.userManagement.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userInfoService {

    @Autowired
    private userRepository repository;          // injecting repository into userInfoService
    @Autowired
    private PasswordEncoder bcryptEncoder;
    public userData saveUserData(userData userdata ) {
        userdata.setPassword(bcryptEncoder.encode(userdata.getPassword()));
        return repository.save(userdata);       // save is inbuilt method given by jpa repos
    }


    public userData getuserDatabyID(int id){
        return repository.findById(id).get();
    }


    public String deleteUserbyID(int id){
        repository.deleteById(id);
    return "user removed!!";
    }

    public userData updateUser(userData userdata)
    {
        userData existingUser = repository.findById(userdata.getId()).orElse(null);
        if (existingUser== null)
            return null;
        existingUser.setUserName(userdata.getUserName());  // overwriting the existing user details
        existingUser.setPassword(bcryptEncoder.encode(userdata.getPassword()));
        existingUser.setFirstName(userdata.getFirstName());
        existingUser.setLastName(userdata.getLastName());
        existingUser.setEmailID(userdata.getEmailID());
        existingUser.setMobileNumber(userdata.getMobileNumber());
        existingUser.setAddress1(userdata.getAddress1());
        existingUser.setAddress2(userdata.getAddress2());
        return repository.save(existingUser);
    }

    public List<userData> listAll() { return repository.findAll(); }

    public List<userData> findByEmailID(String emailid) { return repository.findByEmailID(emailid); }
    public List<userData> findbyUserName(String username) { return repository.findByUserName(username); }
    public List<userData> findbyMobileNumber(long mobilenumber) {return repository.findByMobileNumber(mobilenumber);}

}
