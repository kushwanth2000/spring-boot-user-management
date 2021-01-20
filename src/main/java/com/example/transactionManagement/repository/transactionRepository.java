package com.example.transactionManagement.repository;

import com.example.transactionManagement.entity.transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface transactionRepository extends JpaRepository <transaction, Long> {
}
