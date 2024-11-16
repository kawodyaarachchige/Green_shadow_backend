package com.example.green_shadow.service;

import com.example.green_shadow.dto.impl.EquipmentDTO;
import com.example.green_shadow.entity.Status;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    List<EquipmentDTO> getAllEquipment();
    void updateEquipment(String equipmentId,EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
    EquipmentDTO findEquipment(String equipmentId);
    List<EquipmentDTO> getEquipmentByName(String name);
    List<EquipmentDTO> getEquipmentByStatus( Status status);
}
