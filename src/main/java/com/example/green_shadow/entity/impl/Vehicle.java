package com.example.green_shadow.entity.impl;

import com.example.green_shadow.entity.FuelType;
import com.example.green_shadow.entity.Status;
import com.example.green_shadow.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Vehicle implements SuperEntity {
    @Id
    private String vehicleCode;
    @Column(unique = true)
    private String licensePlateNumber;
    private String vehicleCategory;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;
}
