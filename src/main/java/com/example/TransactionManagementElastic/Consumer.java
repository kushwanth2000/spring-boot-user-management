package com.example.TransactionManagementElastic;


import com.example.TransactionManagementElastic.Model.TransactionElastic;
import com.example.TransactionManagementElastic.Repository.transactionElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {
      @Autowired
  private  transactionElasticSearchRepository transactionElasticRepository;        // defining reference

    @KafkaListener(topics = "Transaction_Data", groupId = "group_json", containerFactory = "kafkaListenerContainerFactory")
    public void consumeTransaction(TransactionElastic Transaction) throws IOException {
        transactionElasticRepository.save(Transaction);
        System.out.println("Consumer JSON message : " + Transaction);
    }
}