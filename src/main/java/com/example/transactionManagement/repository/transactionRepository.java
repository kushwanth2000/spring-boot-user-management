package com.example.transactionManagement.repository;

import com.example.transactionManagement.entity.transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface transactionRepository extends JpaRepository <transaction, Integer> {

    public List<transaction> findBysenderPhone(long senderPhone);
    public List<transaction> findByreceiverPhone(long receiverPhone);


}
