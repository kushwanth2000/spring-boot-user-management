package com.example.walletManagement.repository;

import com.example.walletManagement.entity.walletUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface walletRepository extends JpaRepository<walletUserInfo, UUID> // enitiy name, primary key type
{

    public List<walletUserInfo> findByPhoneNumber(long phoneNumber);

}
