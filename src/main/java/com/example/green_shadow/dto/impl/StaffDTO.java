package com.example.green_shadow.dto.impl;

import com.example.green_shadow.dto.SuperDTO;
import com.example.green_shadow.entity.Gender;
import com.example.green_shadow.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StaffDTO implements SuperDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date joinedDate;
    private Date dob;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String contactNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private List<FieldDTO> fields;
    private List<VehicleDTO> vehicles;
    private List<EquipmentDTO> equipments;
    private String logCode;
}
