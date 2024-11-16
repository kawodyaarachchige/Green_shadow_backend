package com.example.green_shadow.dto.impl;

import com.example.green_shadow.dto.SuperDTO;
import com.example.green_shadow.entity.FuelType;
import com.example.green_shadow.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VehicleDTO  implements SuperDTO {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String remarks;
    private String staff;
}
