package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.UserDAO;
import com.example.green_shadow.entity.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User fetchedUser=userDAO.findByEmail(email);
       if(fetchedUser==null){
           throw new UsernameNotFoundException("User not found");

       }
       return fetchedUser;

    }



}
