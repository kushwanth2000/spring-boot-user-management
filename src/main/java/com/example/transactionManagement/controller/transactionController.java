package com.example.transactionManagement.controller;


import com.example.transactionManagement.entity.transaction;
import com.example.transactionManagement.repository.transactionRepository;
import com.example.transactionManagement.service.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

       return transactionservice.saveTransactiondata(usertransaction);

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

//       @GetMapping("/transactiondetailsbyuserid/{userphonenumber}/pageno{pageno}/pagesize{pagesize}")
//       public ResponseEntity<List<transaction>> transactiondetailsbyuserid(@PathVariable long userphonenumber,
//                                                                           @PathVariable int pageno,
//                                                                           @PathVariable int pagesize)
//    {
//
//        List<transaction> list = transactionservice.transactiondetailsbyuserid(userphonenumber, pageno, pagesize);
//
//        return new ResponseEntity<List<transaction>>(list, new HttpHeaders(), HttpStatus.OK);
////       try {
////           List<transaction> transactionsAsSender = transactionservice.findSenderByMobileNumber(userphonenumber);
////           List<transaction> transactionsAsReceiver = transactionservice.findReceiverByMobileNumber(userphonenumber);
////           /*Creating new array list for merging two list  */
////           List<transaction> allUserTransations = new ArrayList<transaction>();
////           allUserTransations.addAll(transactionsAsReceiver);
////           allUserTransations.addAll(transactionsAsSender);
////           int listsize = allUserTransations.size();                      // total size of the list
////           int pageendingcount = ((pageno - 1) * pagesize) + pagesize;     // total count untill ending of pageno
////           int pagestartingcount = ((pageno - 1) * pagesize);            // total count untill starting of page
////
////           if(pagestartingcount > listsize)
////               return new ResponseEntity<>(HttpStatus.NOT_FOUND);     // for pageno out of bound
////           else if (pageendingcount > listsize)
////               return new ResponseEntity<>(allUserTransations.subList(pagestartingcount, listsize),HttpStatus.ACCEPTED);
////               else
////               return new ResponseEntity<>(allUserTransations.subList(pagestartingcount, pageendingcount),HttpStatus.ACCEPTED);
////       }
////       catch (NoSuchElementException e){
////           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////       }
//    }

}
