package com.example.green_shadow.service.impl;

import com.example.green_shadow.dto.impl.UserDTO;
import com.example.green_shadow.secure.JWTAuthResponse;
import com.example.green_shadow.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

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
