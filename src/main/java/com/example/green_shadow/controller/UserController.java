package com.example.green_shadow.controller;

import com.example.green_shadow.dto.impl.UserDTO;
import com.example.green_shadow.secure.JWTAuthResponse;
import com.example.green_shadow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody UserDTO userDTO) {
        System.out.println("controller");
       userService.register(userDTO);
       return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.login(userDTO));

    }
    @PostMapping(value = "/refresh")
    public ResponseEntity<JWTAuthResponse> refresh(@RequestParam String accessToken) {
        return ResponseEntity.ok(userService.refresh(accessToken)) ;

    }

}
