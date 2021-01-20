package com.example.transactionManagement.controller;


import com.example.transactionManagement.entity.transaction;
import com.example.transactionManagement.service.transactionService;
import com.example.walletManagement.entity.walletUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class transactionController {
    @Autowired
    private transactionService transactionservice;

    @PostMapping("/transaction")
    public String transactiondata(@RequestBody transaction usertransaction) {
        List<walletUserInfo> senderphoneNumber = transactionservice.findByPhoneNumber(usertransaction.getSenderPhone());
        List<walletUserInfo> receiverphoneNumber = transactionservice.findByPhoneNumber(usertransaction.getReceiverPhone());

        int amountTobeTransfered = usertransaction.getAmount();

        if (!senderphoneNumber.isEmpty()) {
            int senderBal = senderphoneNumber.get(0).getBalance();
            if (!receiverphoneNumber.isEmpty()) {
                if (senderBal >= amountTobeTransfered) {
                    transactionservice.updateUserWallet(senderphoneNumber.get(0), -(usertransaction.getAmount()));
                    transactionservice.updateUserWallet(receiverphoneNumber.get(0), usertransaction.getAmount());
                    usertransaction.setStatus("Successful");
                    usertransaction.setRemarks("transaction done successfully");
                    transactionservice.saveTransactiondata(usertransaction);
                    return "transaction done successfully";
                } else {
                    usertransaction.setStatus("Failed");
                    usertransaction.setRemarks("balance is insufficient");
                    transactionservice.saveTransactiondata(usertransaction);
                    return "balance is insufficient";
                }
            } else {
                usertransaction.setStatus("Failed");
                usertransaction.setRemarks("received doesn't exits");
                transactionservice.saveTransactiondata(usertransaction);
                return "received doesn't exits";
            }
        }
        else {
            usertransaction.setStatus("Failed");
            usertransaction.setRemarks("sender doesn't exists");
            transactionservice.saveTransactiondata(usertransaction);
            return "sender doesn't exists";
                }
    }

    @GetMapping("/transactiondetailsbyid/{id}")
    public transaction transactionDetailbyid(@PathVariable  int id){return transactionservice.transactiondetailsbyid(id);}


}
