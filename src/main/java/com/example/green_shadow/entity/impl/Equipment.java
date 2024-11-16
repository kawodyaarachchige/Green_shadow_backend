package com.example.green_shadow.entity.impl;

import com.example.green_shadow.entity.Status;
import com.example.green_shadow.entity.EquipmentType;
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
public class Equipment implements SuperEntity {
    @Id
    private String equipmentId;
    private String name;
    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private Field field;
}
