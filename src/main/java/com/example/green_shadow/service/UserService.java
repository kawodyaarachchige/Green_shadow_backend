package com.example.green_shadow.service;

import com.example.green_shadow.dto.impl.UserDTO;
import com.example.green_shadow.secure.JWTAuthResponse;

public interface UserService {
    void register(UserDTO userDTO);
    JWTAuthResponse login(UserDTO userDTO);
    JWTAuthResponse refresh(String accessToken);
    void update (UserDTO userDTO);
    void delete (UserDTO userDTO);

}
