package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.FieldDAO;
import com.example.green_shadow.dao.StaffDAO;
import com.example.green_shadow.dto.impl.StaffDTO;
import com.example.green_shadow.entity.Gender;
import com.example.green_shadow.entity.impl.Field;
import com.example.green_shadow.entity.impl.Staff;
import com.example.green_shadow.exception.NoSuchEntityException;
import com.example.green_shadow.service.LogService;
import com.example.green_shadow.service.StaffService;
import com.example.green_shadow.util.AppUtil;
import com.example.green_shadow.util.Mapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private LogService logService;
    @Autowired
    private FieldDAO fieldDAO;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        staffDTO.setId(AppUtil.generateStaffId());
        staffDAO.save(mapping.mapToStaff(staffDTO));
        log.info("Staff saved with id {}", staffDTO.getId());
    }

    @Override
    public List<StaffDTO> getStaffs() {
        return mapping.mapToStaffDTOList(staffDAO.findAll());
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {
        Optional<Staff> fetchedStaff = staffDAO.findById(id);
        if (fetchedStaff.isPresent()) {
            Staff staff = fetchedStaff.get();
            staff.setFirstName(staffDTO.getFirstName());
            staff.setLastName(staffDTO.getLastName());
            staff.setDesignation(staffDTO.getDesignation());
            staff.setGender(staffDTO.getGender());
            staff.setJoinedDate(staffDTO.getJoinedDate());
            staff.setAddressLine1(staffDTO.getAddressLine1());
            staff.setAddressLine2(staffDTO.getAddressLine2());
            staff.setAddressLine3(staffDTO.getAddressLine3());
            staff.setAddressLine4(staffDTO.getAddressLine4());
            staff.setAddressLine5(staffDTO.getAddressLine5());
            staff.setContactNumber(staffDTO.getContactNumber());
            staff.setEmail(staffDTO.getEmail());
            staff.setRole(staffDTO.getRole());
            staff.setDob(staffDTO.getDob());
            staff.setLog(mapping.mapToLog(logService.findLog(staffDTO.getLogCode())));
            staffDAO.save(staff);
            log.info("Staff updated with id {}", id);
        }
        else {
            throw new NoSuchEntityException("Staff", id);
        }

    }

    @Override
    public void deleteStaff(String id) {
    staffDAO.deleteById(id);
    log.info("Staff deleted with id {}", id);
    }

    @Override
    public StaffDTO findStaff(String id) {
        Optional<Staff> fetchedStaff = staffDAO.findById(id);
        if(fetchedStaff.isPresent()){
            Staff staff = fetchedStaff.get();
            return mapping.mapToStaffDTO(staff);
        }else {
            throw new NoSuchEntityException("Staff",id);
        }
    }

    @Override
    public List<StaffDTO> findByStaffName(String staffName) {
        return mapping.mapToStaffDTOList(staffDAO.findByStaffName(staffName));
    }

    @Override
    public List<StaffDTO> findByDesignation(String designation) {
        return mapping.mapToStaffDTOList(staffDAO.findByDesignation(designation));
    }

    @Override
    public List<StaffDTO> sortByGender(Gender gender) {
        return mapping.mapToStaffDTOList(staffDAO.sortByGender(gender));
    }

    @Override
    public void assignFieldToStaff(String staffId, String fieldCode) {
        Staff fetchedStaff = staffDAO.findById(staffId).orElseThrow(() -> new NoSuchEntityException("Staff", staffId));
        Field field = fieldDAO.findById(fieldCode).orElseThrow(() -> new NoSuchEntityException("Field", fieldCode));
        fetchedStaff.addField(field);
        staffDAO.save(fetchedStaff);
        log.info("Field assigned to staff with id {}", staffId);
    }

    @Override
    public void removeFieldFromStaff(String staffId, String fieldCode) {
        Staff fetchedStaff = staffDAO.findById(staffId).orElseThrow(() -> new NoSuchEntityException("Staff", staffId));
        Field field = fieldDAO.findById(fieldCode).orElseThrow(() -> new NoSuchEntityException("Field", fieldCode));
        fetchedStaff.removeField(field);
        staffDAO.save(fetchedStaff);
        log.info("Field removed from staff with id {}", staffId);

    }

    @Override
    public List<StaffDTO> findStaffByFieldCode(String fieldCode) {
        return mapping.mapToStaffDTOList(staffDAO.findStaffByFieldCode(fieldCode));
    }
}
