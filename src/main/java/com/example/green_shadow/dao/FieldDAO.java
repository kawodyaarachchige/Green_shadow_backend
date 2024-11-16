package com.example.green_shadow.dao;

import com.example.green_shadow.entity.impl.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldDAO extends JpaRepository<Field, String> {
    @Query("SELECT f FROM Field f WHERE f.extentSizeOfField >= :extentSizeOfField")
    List<Field> findByExtentSizeOfField(Double extentSizeOfField);
}
