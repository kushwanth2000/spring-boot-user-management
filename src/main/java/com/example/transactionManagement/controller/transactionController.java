package com.example.transactionManagement.controller;


import com.example.transactionManagement.entity.transaction;
import com.example.transactionManagement.repository.transactionRepository;
import com.example.transactionManagement.service.transactionService;
import com.example.userManagement.entity.userData;
import com.example.walletManagement.entity.walletUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class transactionController {
    @Autowired
    private transactionService transactionservice;

    @Autowired
    private transactionRepository repository;

    @PostMapping("/transaction")
    public String transactiondata(@RequestBody transaction usertransaction) {
        List<walletUserInfo> senderphoneNumber = transactionservice.findByPhoneNumber(usertransaction.getSenderPhone());
        List<walletUserInfo> receiverphoneNumber = transactionservice.findByPhoneNumber(usertransaction.getReceiverPhone());

        // Sender and receiver same phone number validation
        if(senderphoneNumber.get(0).getPhoneNumber()==receiverphoneNumber.get(0).getPhoneNumber())
        {return "Sender and receiver cannot be same"; }
        int amountTobeTransfered = usertransaction.getAmount();

        if (!senderphoneNumber.isEmpty()) {                 /* sender wallet exists or not validation*/
            int senderBal = senderphoneNumber.get(0).getBalance();       // fetching sender current balance
            if (!receiverphoneNumber.isEmpty()) {             /* receiver wallet exists or not validation*/
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


    @GetMapping("/transactiondetailsbyid/{id}")
    public transaction transactionDetailbyid(@PathVariable  int id)
    {
        return transactionservice.transactiondetailsbyid(id);
    }




//    Page <transaction> allUserTransation( @PathVariable int pageno, @PathVariable int size)
//    {
////        List <transaction> transactionsAsSender = transactionservice.findSenderByMobileNumber(userphonenumber);
////        List <transaction> transactionsAsReceiver =transactionservice.findReceiverByMobileNumber(userphonenumber);
////        List <transaction> allUserTransations = new ArrayList<transaction>();
////         allUserTransations.addAll(transactionsAsReceiver);
////         allUserTransations.addAll(transactionsAsSender);
//
//           Model model
//        int currentPage = pageno
//        int pageSize = size
//
//        Page<transaction> bookPage = transactionservice.findPaginated(PageRequest.of(currentPage - 1, pageSize));
//
//        model.addAttribute("bookPage", bookPage);
//
//        int totalPages = bookPage.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//
//
//
//
//    }

       @GetMapping("/transactiondetailsbyuserid/{userphonenumber}/{pageno}/{pagesize}")
       public List<transaction> transactiondetailsbyuserid(@PathVariable long userphonenumber, @PathVariable int pageno , @PathVariable int pagesize)
    {
           List<transaction> transactionsAsSender = transactionservice.findSenderByMobileNumber(userphonenumber);
           List<transaction> transactionsAsReceiver = transactionservice.findReceiverByMobileNumber(userphonenumber);
           /*Creating new array list for merging two list  */
           List<transaction> allUserTransations = new ArrayList<transaction>();
           allUserTransations.addAll(transactionsAsReceiver);
           allUserTransations.addAll(transactionsAsSender);
           int listsize = allUserTransations.size();
           int pageendingcount = ((pageno - 1) * pagesize) + pagesize;     // total count untill ending of pageno
           int pagestartingcount = ((pageno - 1) * pagesize);            // total count untill starting of page
           if (pageendingcount > listsize)
               return allUserTransations.subList(pagestartingcount, listsize);
           else
               return allUserTransations.subList(pagestartingcount, pageendingcount);

    }
}
