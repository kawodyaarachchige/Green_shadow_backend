package com.example.green_shadow.dto.impl;

import com.example.green_shadow.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserPasswordRenewRequestDTO implements SuperDTO {
    private UserDTO userDTO;
    private String newPassword;
}
