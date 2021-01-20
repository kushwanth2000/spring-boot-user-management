package com.example.transactionManagement.controller;


import com.example.transactionManagement.entity.transaction;
import com.example.transactionManagement.service.transactionService;
import com.example.walletManagement.entity.walletUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class transactionController {
    @Autowired
    private transactionService transactionservice;

    @PostMapping("/transaction")
    public String transactiondata(@RequestBody transaction usertransaction) {
        List<walletUserInfo> senderphoneNumber = transactionservice.findByPhoneNumber(usertransaction.getSenderPhone());
        List<walletUserInfo> receiverphoneNumber = transactionservice.findByPhoneNumber(usertransaction.getReceiverPhone());

        int senderBal= senderphoneNumber.get(0).getBalance();
        int amountTobeTransfered=usertransaction.getAmount();

        if(!senderphoneNumber.isEmpty())
        {
            if(!receiverphoneNumber.isEmpty())
            {
                if(senderBal >= amountTobeTransfered){
                    transactionservice.updateUserWallet(senderphoneNumber.get(0),-(usertransaction.getAmount()));
                    transactionservice.updateUserWallet(receiverphoneNumber.get(0),usertransaction.getAmount());
                      transactionservice.saveTransactiondata(usertransaction);
                    return "transaction done successfully";}

                else return "balance is insufficient";

            }
            else return "received doesn't exits";
        }
        else return "sender doesn't exists";

    }
     }
