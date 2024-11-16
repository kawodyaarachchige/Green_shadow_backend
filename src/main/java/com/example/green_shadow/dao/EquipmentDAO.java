package com.example.green_shadow.dao;

import com.example.green_shadow.entity.Status;
import com.example.green_shadow.entity.impl.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentDAO extends JpaRepository<Equipment, String> {
    @Query("SELECT e FROM Equipment e WHERE e.name LIKE CONCAT(:name, '%')")
    List<Equipment> findByName(String name);

    @Query("SELECT e FROM Equipment e WHERE e.status = :status")
    List<Equipment> findByStatus(Status status);
}
