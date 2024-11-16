package com.example.green_shadow.service;

import com.example.green_shadow.dto.impl.VehicleDTO;
import com.example.green_shadow.entity.Status;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    List<VehicleDTO> getAllVehicles();
    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleCode);
    VehicleDTO findVehicle(String vehicleCode);
    List<VehicleDTO> sortByVehicleCategory(String name);
    List<VehicleDTO> findByStatus(Status status);
}
