package com.example.walletManagement.service;


import com.example.walletManagement.repository.walletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class walletService {

    @Autowired
    private walletRepository repository;


}
