package com.example.green_shadow.dao;

import com.example.green_shadow.entity.impl.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDAO  extends JpaRepository<Staff, String> {
}
