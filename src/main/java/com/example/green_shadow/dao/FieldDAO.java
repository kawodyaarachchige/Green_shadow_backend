package com.example.green_shadow.dao;

import com.example.green_shadow.entity.impl.Field;
import com.example.green_shadow.entity.impl.Log;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldDAO extends JpaRepository<Field, String> {
    @Query("SELECT f FROM Field f WHERE f.extentSizeOfField >= :extentSizeOfField")
    List<Field> findByExtentSizeOfField(Double extentSizeOfField);

    @Transactional
    @Modifying
    @Query("DELETE FROM Field f WHERE f.fieldCode = :fieldCode")
    void deleteFieldByFieldCode(@Param("fieldCode") String fieldCode);

    @Query("SELECT f.log FROM Field f WHERE f.fieldCode = :fieldCode")
    Optional<Log> findLogByFieldCode(@Param("fieldCode") String fieldCode);
}
