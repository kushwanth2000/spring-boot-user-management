package com.example.walletManagement.repository;

import com.example.walletManagement.entity.walletUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface walletRepository extends JpaRepository<walletUserInfo, Long> // enitiy name, primary key type
{

    public List<walletUserInfo> findByPhoneNumber(long phoneNumber);
}
