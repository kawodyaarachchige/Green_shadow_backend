package com.example.green_shadow.entity.impl;

import com.example.green_shadow.entity.Role;
import com.example.green_shadow.entity.SuperEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class User implements SuperEntity {
    @Id
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
