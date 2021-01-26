package com.example.TransactionManagementElastic.Controller;


import com.example.TransactionManagementElastic.Model.TransactionElastic;
import com.example.TransactionManagementElastic.Service.TransactionElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class transactionElascticController {
     @Autowired
    private TransactionElasticService transelasticservice;

    @PostMapping(value = "/elastictransaction")               // post mapping
    public String transactiondata(@RequestBody TransactionElastic usertransaction){
       return transelasticservice.saveTransactiondata(usertransaction);
    }

    @GetMapping(value = "/elastictransaction/all")        //GET mapping
    public Iterable<TransactionElastic> displayAll(){
        return transelasticservice.allTransactiondata();
    }


     //GET mapping  for user transactions

       @GetMapping("/elastictransactiondetailsbyuserid/{userphonenumber}")
       public List<TransactionElastic> transactiondetailsbyuserid(@PathVariable long userphonenumber) {

           List<TransactionElastic> transactionsAsSender = transelasticservice.findSenderByMobileNumber(userphonenumber);
           List<TransactionElastic> transactionsAsReceiver = transelasticservice.findReceiverByMobileNumber(userphonenumber);
           /*Creating new array list for merging two list  */
           List<TransactionElastic> allUserTransations = new ArrayList<TransactionElastic>();
           allUserTransations.addAll(transactionsAsReceiver);
           allUserTransations.addAll(transactionsAsSender);
           return allUserTransations;
       }

}
