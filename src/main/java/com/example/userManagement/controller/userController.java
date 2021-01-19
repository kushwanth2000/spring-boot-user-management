package com.example.userManagement.controller;

import com.example.userManagement.entity.userData;
import com.example.userManagement.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {
    @Autowired
    private userInfoService service;


    @PostMapping("/adduser")          //POST Mapping
    public String addUser(@RequestBody userData userdata)
    {
        List<userData> user_email = service.findByEmailID(userdata.getEmailID());
        List<userData> user_username = service.findbyUserName(userdata.getUserName());
        List<userData> user_mobile_number = service.findbyMobileNumber(userdata.getMobileNumber());

                                    // checking for existing Users
        if(!user_email.isEmpty())   {return "User with same emailID already exists";}
        else if (!user_username.isEmpty() ) {return "User with same userName already exists";}
        else if (!user_mobile_number.isEmpty()) {return "User with same mobileNumber already exists";}
        else  {service.saveUserData(userdata);}
        return "User saved";
    }


    @GetMapping("/userbyID/{id}")            //GET Mapping
    public userData findUserbyID(@PathVariable int id)
    {
        return service.getuserDatabyID(id);
    }

    @DeleteMapping("/delete/{id}")              //Delete Mapping
    public String deleteUser(@PathVariable int id)
    {
        return service.deleteUserbyID(id);
    }

    @PutMapping("updateuser")                  //PUT Mapping
    public String updateUser(@RequestBody userData userdata)
    {
        userData existingUser = service.updateUser(userdata); // Checking for User exist or not
        if(existingUser == null)
        {
            return "user not existed";
        }
        else return "user data updated";
    }

}
