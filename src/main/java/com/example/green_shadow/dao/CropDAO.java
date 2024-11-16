package com.example.green_shadow.dao;

import com.example.green_shadow.entity.impl.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropDAO extends JpaRepository<Crop, String> {
}
