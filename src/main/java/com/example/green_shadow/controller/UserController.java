package com.example.green_shadow.controller;

import com.example.green_shadow.dto.impl.UserDTO;
import com.example.green_shadow.dto.impl.UserPasswordRenewRequestDTO;
import com.example.green_shadow.secure.JWTAuthResponse;
import com.example.green_shadow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping(value = "/password",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody UserPasswordRenewRequestDTO userDTO){
        userService.updatePassword(userDTO.getUserDTO(),userDTO.getNewPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/role",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUserRole(@RequestBody UserDTO userDTO){
        userService.updateUserRole(userDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@RequestBody UserDTO userDTO){
        userService.delete(userDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/get-role",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> getUserRole(@RequestBody UserDTO userDTO){
        String userRole = userService.getUserRole(userDTO);
        return ResponseEntity.ok(Map.of("role",userRole));
    }
}
