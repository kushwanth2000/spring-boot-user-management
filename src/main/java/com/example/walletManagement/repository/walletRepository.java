package com.example.walletManagement.repository;

import com.example.walletManagement.entity.walletUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface walletRepository extends JpaRepository<walletUserInfo, Long> // enitiy name, primary key type
{

}
