package com.example.userManagement;

import com.example.userManagement.entity.userData;
import com.example.userManagement.repository.userRepository;
import com.example.userManagement.service.userInfoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class userControllerTest {

    @Autowired
    private userInfoService userinfoservice;

    @MockBean
    private userRepository  userrepository;





    public List<userData> getTestList() {
        userData user1 = new userData(1, "user1","pass", "s", "t", "email1",
                 9089,"a1", "a2");
        userData user2 = new userData(2, "user2","pass", "p", "q", "email2",
                 90891,"a3", "a4");
        List<userData> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        return userList;
    }

    @Test
    public void getAllUsersTest() {
        List<userData> userList = getTestList();
        when(userrepository.findAll()).thenReturn(userList.stream().collect(Collectors.toList()));
        assertEquals(2, userinfoservice.listAll().size());
    }

    @Test
    public void getByEmailTest() {
        String emailID = "email1";
        List<userData> userList = getTestList();
        when(userrepository.findByEmailID(emailID)).thenReturn(userList.stream().collect(Collectors.toList()));
        assertEquals(emailID, userinfoservice.findByEmailID(emailID).get(0).getEmailID());
    }


}