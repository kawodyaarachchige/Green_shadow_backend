package com.example.green_shadow.dao;

import com.example.green_shadow.entity.Status;
import com.example.green_shadow.entity.impl.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDAO extends JpaRepository<Vehicle, String> {
    @Query("SELECT v FROM Vehicle v WHERE v.vehicleCategory LIKE CONCAT(:vehicleCategory, '%')")
    List<Vehicle> sortByVehicleCategory(String vehicleCategory);

    @Query("SELECT v FROM Vehicle v WHERE v.status = :status")
    List<Vehicle> findByStatus(Status status);


}
