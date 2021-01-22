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


    public String saveTransactiondata(transaction usertransaction)
    {
        List<walletUserInfo> senderphoneNumber = walletrepository.findByPhoneNumber(usertransaction.getSenderPhone());
        List<walletUserInfo> receiverphoneNumber = walletrepository.findByPhoneNumber(usertransaction.getReceiverPhone());

        int amountTobeTransfered = usertransaction.getAmount();

        if (!senderphoneNumber.isEmpty()) {                 /* sender wallet exists or not validation*/
            int senderBal = senderphoneNumber.get(0).getBalance();       // fetching sender current balance
            if (!receiverphoneNumber.isEmpty()) {             /* receiver wallet exists or not validation*/

                // Sender and receiver same phone number validation
                if(senderphoneNumber.get(0).getPhoneNumber()==receiverphoneNumber.get(0).getPhoneNumber())
                {return "Sender and receiver cannot be same"; }

                if (senderBal >= amountTobeTransfered) {
                    usertransaction.setStatus("Pending");
                    usertransaction.setRemarks("transaction Pending");
                    transactionrepository.save(usertransaction);
                    updateUserWallet(senderphoneNumber.get(0), -(usertransaction.getAmount()));
                    //    if(amountTobeTransfered == 100){throw new RuntimeException("manuel run time"); }
                    updateUserWallet(receiverphoneNumber.get(0), usertransaction.getAmount());
                    usertransaction.setStatus("Successful");
                    usertransaction.setRemarks("transaction done successfully");
                    transactionrepository.save(usertransaction);  // transaction details are saved
                    return "transaction done successfully";
                } else {
                    usertransaction.setStatus("Failed");
                    usertransaction.setRemarks("balance is insufficient");
                    transactionrepository.save(usertransaction);
                    return "balance is insufficient";
                }
            } else {
                usertransaction.setStatus("Failed");
                usertransaction.setRemarks("receiver wallet doesn't exits");
                transactionrepository.save(usertransaction);
                return "receiver wallet doesn't exits";
            }
        }
        else {
            usertransaction.setStatus("Failed");
            usertransaction.setRemarks("sender wallet doesn't exists");
            transactionrepository.save(usertransaction);
            return "sender wallet doesn't exists";
        }

    }


    public transaction transactiondetailsbyid(int id){ return transactionrepository.findById(id).get();}


    public String updateUserWallet(walletUserInfo existingWalletuser,int amount)
    {
        int finalAmount=existingWalletuser.getBalance()+amount;
        existingWalletuser.setBalance(finalAmount);                // updating the wallet balance
        walletrepository.save(existingWalletuser);
        return "wallet updated";
    }



    public List<walletUserInfo> findByPhoneNumber(long phoneNumber)
             {return walletrepository.findByPhoneNumber(phoneNumber);}
    public List<transaction> findSenderByMobileNumber(long senderNumber)
             {return  transactionrepository.findBysenderPhone(senderNumber);}
    public List<transaction> findReceiverByMobileNumber(long receiverNumber)
             {return transactionrepository.findByreceiverPhone(receiverNumber);}


}

