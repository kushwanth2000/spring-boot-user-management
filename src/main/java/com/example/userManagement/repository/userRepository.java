package com.example.userManagement.repository;

import com.example.userManagement.entity.userData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface userRepository extends JpaRepository<userData, Integer>   // enitiy obj name, primary key type
{
    public List<userData> findByEmailID(String emailID);
    public List<userData> findByUserName(String userName);
    public List<userData> findByMobileNumber(long mobileNumber);

}