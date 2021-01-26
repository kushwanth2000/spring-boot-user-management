package com.example.TransactionManagementElastic.Service;

import com.example.TransactionManagementElastic.Model.TransactionElastic;
import com.example.TransactionManagementElastic.Repository.transactionElasticSearchRepository;

import com.example.transactionManagement.entity.transaction;
import com.example.userManagement.repository.userRepository;
import com.example.walletManagement.entity.walletUserInfo;
import com.example.walletManagement.repository.walletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionElasticService {


    @Autowired
    transactionElasticSearchRepository transactionElasticRepository;        // defining reference


    @Autowired
    private walletRepository walletrepository;
    @Autowired
    private userRepository userrepository;

   

     // GET mapping for all transactions
    public Iterable<TransactionElastic> allTransactiondata(){
        return transactionElasticRepository.findAll();
    }


    @Transactional  // POST for transaction
    public String saveTransactiondata(TransactionElastic usertransaction) {
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
                    updateUserWallet(senderphoneNumber.get(0), -(usertransaction.getAmount()));
                    // if(amountTobeTransfered == 100){throw new RuntimeException("manuel run time"); }
                    // checking for runtime expection after amount debited
                    updateUserWallet(receiverphoneNumber.get(0), usertransaction.getAmount());
                    usertransaction.setStatus("Successful");
                    usertransaction.setRemarks("transaction done successfully");
                    kafkatransactionTemplate.send(transactionTopic,usertransaction);  // publishing transaction data onto Kafka topic
                    return "transaction done successfully";
                } else {
                    usertransaction.setStatus("Failed");
                    usertransaction.setRemarks("balance is insufficient");
                    kafkatransactionTemplate.send(transactionTopic,usertransaction);
                    return "balance is insufficient";
                }
            } else {
                usertransaction.setStatus("Failed");
                usertransaction.setRemarks("receiver wallet doesn't exits");
                kafkatransactionTemplate.send(transactionTopic,usertransaction);
                return "receiver wallet doesn't exits";
            }
        }
        else {
            usertransaction.setStatus("Failed");
            usertransaction.setRemarks("sender wallet doesn't exists");
            kafkatransactionTemplate.send(transactionTopic,usertransaction);
            return "sender wallet doesn't exists";
        }

    }



    public String updateUserWallet(walletUserInfo existingWalletuser,int amount)
    {
        int finalAmount=existingWalletuser.getBalance()+amount;
        existingWalletuser.setBalance(finalAmount);                // updating the wallet balance
        walletrepository.save(existingWalletuser);
        return "wallet updated";
    }


    public List<walletUserInfo> findByPhoneNumber(long phoneNumber)
    {return walletrepository.findByPhoneNumber(phoneNumber);}
    public List<TransactionElastic> findSenderByMobileNumber(long senderNumber)
    {return  transactionElasticRepository.findBysenderPhone(senderNumber); }
    public List<TransactionElastic> findReceiverByMobileNumber(long receiverNumber)
    {return  transactionElasticRepository.findByreceiverPhone(receiverNumber);}

}
