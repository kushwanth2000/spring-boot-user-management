package com.example.userManagement.controller;

import com.example.userManagement.entity.userData;
import com.example.userManagement.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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


/* added try catch method for non existed user*/
    @GetMapping("/userbyID/{id}")            //GET Mapping
    public ResponseEntity<userData> findUserbyID(@PathVariable int id) {
        try {
            userData userdata = service.getuserDatabyID(id);
            return new ResponseEntity<userData>(userdata, HttpStatus.ACCEPTED);
        } catch (
                NoSuchElementException e) {
            return new ResponseEntity<userData>(HttpStatus.NOT_FOUND);
        }
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
