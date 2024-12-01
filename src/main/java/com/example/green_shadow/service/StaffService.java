package com.example.green_shadow.service;

import com.example.green_shadow.dto.impl.StaffDTO;
import com.example.green_shadow.entity.Gender;

import java.util.List;

public interface StaffService {
    String saveStaff(StaffDTO staffDTO);
    List<StaffDTO> getStaffs();
    void updateStaff(String id,StaffDTO staffDTO);
    void deleteStaff(String id);
    StaffDTO findStaff(String id);
    List<StaffDTO> findByStaffName(String staffName);
    List<StaffDTO> findByDesignation(String designation);
    List<StaffDTO> sortByGender(Gender gender);
    void assignFieldToStaff(String staffId, String fieldCode);
    void removeFieldFromStaff(String staffId, String fieldCode);
    List <StaffDTO> findStaffByFieldCode(String fieldCode);

}
