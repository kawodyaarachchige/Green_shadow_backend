package com.example.green_shadow.dao;

import com.example.green_shadow.entity.impl.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CropDAO extends JpaRepository<Crop, String> {
    @Query("SELECT e FROM Crop e WHERE e.cropCommonName LIKE CONCAT(:name, '%') OR e.cropScientificName LIKE CONCAT(:name, '%')")
    List<Crop> findByName(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Crop c WHERE c.cropCode = :cropCode")
    void deleteByCropCode(@Param("cropCode") String cropCode);
}
