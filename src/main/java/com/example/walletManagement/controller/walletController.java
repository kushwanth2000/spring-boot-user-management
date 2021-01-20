package com.example.walletManagement.controller;

import com.example.userManagement.entity.userData;
import com.example.walletManagement.entity.walletUserInfo;
import com.example.walletManagement.service.walletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class walletController {

    @Autowired
    walletService walletserivce;



    @PostMapping("/createwallet")
    public String createWallet(@RequestBody walletUserInfo walletuserinfo)
    {
        List<userData> userphonenumber = walletserivce.findByMobileNumber(walletuserinfo.getPhoneNumber());
        List<walletUserInfo> walletphoneNumber = walletserivce.findByPhoneNumber(walletuserinfo.getPhoneNumber());
            if(!userphonenumber.isEmpty())
            {
                if(walletphoneNumber.isEmpty())
                { walletserivce.saveWallet(walletuserinfo);  return "Wallet created";}
                else     return "Wallet already registered ";
            }
            else return "Phone number not registered in userdata";
    }

}
