package com.example.green_shadow.dto.impl;

import com.example.green_shadow.dto.SuperDTO;
import com.example.green_shadow.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder


public class UserDTO implements SuperDTO {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String roleClarificationCode;
}
