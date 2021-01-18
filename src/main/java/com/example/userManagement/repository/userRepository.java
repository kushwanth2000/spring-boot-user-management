package com.example.userManagement.repository;

import com.example.userManagement.entity.userData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<userData, Integer>   // enitiy name, primary key type
{


}