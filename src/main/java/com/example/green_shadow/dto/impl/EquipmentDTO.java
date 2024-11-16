package com.example.green_shadow.dto.impl;

import com.example.green_shadow.dto.SuperDTO;
import com.example.green_shadow.entity.EquipmentType;
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
public class EquipmentDTO  implements SuperDTO {
    private String equipmentId;
    private String name;
    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String staffId;
    private String fieldCode;
}
