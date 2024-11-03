package com.example.green_shadow.controller;

import com.example.green_shadow.dto.impl.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")

public class UserController {
    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody UserDTO userDTO) {
        return null;

    }
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> login(@RequestBody UserDTO userDTO) {
        return null;

    }
    @PostMapping(value = "/refresh")
    public ResponseEntity<Void> refresh(@RequestParam String accessToken) {
        return null;

    }

}
