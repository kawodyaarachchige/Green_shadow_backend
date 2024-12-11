package com.example.green_shadow.dao;

import com.example.green_shadow.entity.Status;
import com.example.green_shadow.entity.impl.Equipment;
import com.example.green_shadow.entity.impl.Field;
import com.example.green_shadow.entity.impl.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentDAO extends JpaRepository<Equipment, String> {
    @Query("SELECT e FROM Equipment e WHERE e.name LIKE CONCAT(:name, '%')")
    List<Equipment> findByName(String name);

    @Query("SELECT e FROM Equipment e WHERE e.status = :status")
    List<Equipment> findByStatus(Status status);

    @Query("SELECT e.field FROM Equipment e WHERE e.equipmentId = :equipmentId")
    Optional<Field> findFieldByEquipmentId(@Param("equipmentId") String equipmentId);
}
