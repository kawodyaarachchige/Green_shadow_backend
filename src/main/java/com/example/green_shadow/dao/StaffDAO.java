package com.example.green_shadow.dao;

import com.example.green_shadow.entity.Gender;
import com.example.green_shadow.entity.impl.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDAO  extends JpaRepository<Staff, String> {
    @Query("SELECT s FROM Staff s WHERE s.firstName = :staffName OR s.lastName = :staffName")
    List<Staff> findByStaffName(String staffName);

    @Query("SELECT s FROM Staff s WHERE s.designation LIKE CONCAT(:designation, '%')")
    List<Staff> findByDesignation(String designation);

    @Query("SELECT s FROM Staff s WHERE s.gender = :gender")
    List<Staff> sortByGender(Gender gender);
    @Query("SELECT s FROM Field f JOIN f.staff s WHERE f.fieldCode = :fieldCode")
    List<Staff> findStaffByFieldCode(String fieldCode);
}
