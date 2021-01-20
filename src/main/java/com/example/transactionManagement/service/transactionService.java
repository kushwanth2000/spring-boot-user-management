package com.example.transactionManagement.service;


import com.example.transactionManagement.entity.transaction;
import com.example.transactionManagement.repository.transactionRepository;
import com.example.userManagement.entity.userData;
import com.example.userManagement.repository.userRepository;
import com.example.walletManagement.entity.walletUserInfo;
import com.example.walletManagement.repository.walletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class transactionService {
    @Autowired
    private walletRepository walletrepository;
    @Autowired
    private userRepository userrepository;
    @Autowired
    private transactionRepository transactionrepository;

    public transaction saveTransactiondata(transaction usertransaction)
    {
        return transactionrepository.save(usertransaction);
    }

    public transaction transactiondetailsbyid(int id){ return transactionrepository.findById(id).orElse(null);}

    public String updateUserWallet(walletUserInfo existingWalletuser,int amount)
    {
        int finalAmount=existingWalletuser.getBalance()+amount;
        existingWalletuser.setBalance(finalAmount);
        walletrepository.save(existingWalletuser);
        return "wallet updated";
    }

    public List<walletUserInfo> findByPhoneNumber(long phoneNumber){return walletrepository.findByPhoneNumber(phoneNumber);}
}

