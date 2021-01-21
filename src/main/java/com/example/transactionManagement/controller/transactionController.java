package com.example.transactionManagement.controller;


import com.example.transactionManagement.entity.transaction;
import com.example.transactionManagement.repository.transactionRepository;
import com.example.transactionManagement.service.transactionService;
import com.example.walletManagement.entity.walletUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class transactionController {
    @Autowired
    private transactionService transactionservice;

    @Autowired
    private transactionRepository repository;

                                        // Post Mapping to wallet to wallet transfer
    @PostMapping("/transaction")
    public String transactiondata(@RequestBody transaction usertransaction) {

        List<walletUserInfo> senderphoneNumber = transactionservice.findByPhoneNumber(usertransaction.getSenderPhone());
        List<walletUserInfo> receiverphoneNumber = transactionservice.findByPhoneNumber(usertransaction.getReceiverPhone());

        int amountTobeTransfered = usertransaction.getAmount();

        if (!senderphoneNumber.isEmpty()) {                 /* sender wallet exists or not validation*/
            int senderBal = senderphoneNumber.get(0).getBalance();       // fetching sender current balance
            if (!receiverphoneNumber.isEmpty()) {             /* receiver wallet exists or not validation*/

                // Sender and receiver same phone number validation
                if(senderphoneNumber.get(0).getPhoneNumber()==receiverphoneNumber.get(0).getPhoneNumber())
                {return "Sender and receiver cannot be same"; }

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
                usertransaction.setRemarks("receiver wallet doesn't exits");
                transactionservice.saveTransactiondata(usertransaction);
                return "receiver wallet doesn't exits";
            }
        }
        else {
            usertransaction.setStatus("Failed");
            usertransaction.setRemarks("sender wallet doesn't exists");
            transactionservice.saveTransactiondata(usertransaction);
            return "sender wallet doesn't exists";
                }
    }


    // Read Transaction details by ID
    // added try catch method for non exiting transactions
    @GetMapping("/transactiondetailsbyid/{id}")
    public ResponseEntity<transaction> transactionDetailbyid(@PathVariable  int id) {
        try {
            transaction tnx =transactionservice.transactiondetailsbyid(id);
            return new ResponseEntity<transaction>(tnx,HttpStatus.ACCEPTED);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<transaction>(HttpStatus.NOT_FOUND);
        }
    }





   // GET mapping  for user transactions

       @GetMapping("/transactiondetailsbyuserid/{userphonenumber}/pageno{pageno}/pagesize{pagesize}")
       public ResponseEntity<List<transaction>> transactiondetailsbyuserid(@PathVariable long userphonenumber, @PathVariable int pageno , @PathVariable int pagesize)
    {
       try {
           List<transaction> transactionsAsSender = transactionservice.findSenderByMobileNumber(userphonenumber);
           List<transaction> transactionsAsReceiver = transactionservice.findReceiverByMobileNumber(userphonenumber);
           /*Creating new array list for merging two list  */
           List<transaction> allUserTransations = new ArrayList<transaction>();
           allUserTransations.addAll(transactionsAsReceiver);
           allUserTransations.addAll(transactionsAsSender);
           int listsize = allUserTransations.size();                      // total size of the list
           int pageendingcount = ((pageno - 1) * pagesize) + pagesize;     // total count untill ending of pageno
           int pagestartingcount = ((pageno - 1) * pagesize);            // total count untill starting of page

           if(pagestartingcount > listsize)
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);     // for page no out of bound
           else if (pageendingcount > listsize)
               return new ResponseEntity<>(allUserTransations.subList(pagestartingcount, listsize),HttpStatus.ACCEPTED);
               else
               return new ResponseEntity<>(allUserTransations.subList(pagestartingcount, pageendingcount),HttpStatus.ACCEPTED);
       }
       catch (NoSuchElementException e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

}
