package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.UserDAO;
import com.example.green_shadow.dto.impl.UserDTO;
import com.example.green_shadow.secure.JWTAuthResponse;
import com.example.green_shadow.service.UserService;
import com.example.green_shadow.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @Override
    public void register(UserDTO userDTO) {

    }

    @Override
    public JWTAuthResponse login(UserDTO userDTO) {
        return null;
    }

    @Override
    public JWTAuthResponse refresh(String accessToken) {
        return null;
    }
}
