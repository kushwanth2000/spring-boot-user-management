package com.example.userManagement.repository;

import com.example.userManagement.entity.userData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<userData, Integer>   // enitiy obj name, primary key type
{
    /* custom finders*/
    public List<userData> findByEmailID(String emailID);
    public List<userData> findByUserName(String userName);
    public List<userData> findByMobileNumber(long mobileNumber);

}