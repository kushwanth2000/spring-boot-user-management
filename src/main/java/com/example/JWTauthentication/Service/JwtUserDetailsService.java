package com.example.JWTauthentication.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.userManagement.entity.userData;
import com.example.userManagement.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private userInfoService userinfoservice;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

          // Checking that user is registered or not
        List<userData> user= userinfoservice.findbyUserName(username);
        System.out.println(user.isEmpty());
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.get(0).getUserName(),user.get(0).getPassword(),
                new ArrayList<>());

    }


}