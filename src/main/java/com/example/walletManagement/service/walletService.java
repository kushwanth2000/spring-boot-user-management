package com.example.walletManagement.service;


import com.example.userManagement.entity.userData;
import com.example.userManagement.repository.userRepository;
import com.example.walletManagement.entity.walletUserInfo;
import com.example.walletManagement.repository.walletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class walletService {

    @Autowired
    private walletRepository walletrepository;
    @Autowired
    private userRepository userrepository;

    public walletUserInfo saveWallet(walletUserInfo walletuserinfo)
    {
       return walletrepository.save(walletuserinfo);
    }

    public List<userData> findByMobileNumber(long mobilenumber){return userrepository.findByMobileNumber(mobilenumber);}
    public List<walletUserInfo> findByPhoneNumber(long phoneNumber){return walletrepository.findByPhoneNumber(phoneNumber);}
}
