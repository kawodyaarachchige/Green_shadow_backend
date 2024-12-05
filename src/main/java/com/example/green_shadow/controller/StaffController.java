package com.example.green_shadow.controller;

import com.example.green_shadow.dto.impl.StaffDTO;
import com.example.green_shadow.entity.Gender;
import com.example.green_shadow.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Map<String,String>> saveStaff(@RequestBody StaffDTO staffDTO) {
        String savedStaffID = staffService.saveStaff(staffDTO);
        Map<String,String> response = new HashMap<>();
        response.put("staffId",savedStaffID);
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE') or hasRole('SCIENTIST')")
    public List<StaffDTO> getStaff() {
        return staffService.getStaffs();
    }
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> updateStaff(@PathVariable("id") String staffCode, @RequestBody StaffDTO staffDTO) {
        staffService.updateStaff(staffCode, staffDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> deleteStaff(@PathVariable("id") String staffCode) {
        staffService.deleteStaff(staffCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/staffName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getStaffByName(@PathVariable("name") String name) {
        return staffService.findByStaffName(name);
    }
    @GetMapping(value = "/designation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getStaffByDesignation(@PathVariable("designation") String designation) {
        return staffService.findByDesignation(designation);
    }
    @GetMapping(value = "/gender/{gender}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getStaffByGender(@PathVariable("gender") Gender gender) {
        return staffService.sortByGender(gender);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> assignFieldToStaff(@RequestPart("staffId") String staffId, @RequestPart("fieldCode") String fieldCode) {
        staffService.assignFieldToStaff(staffId, fieldCode);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    public ResponseEntity<Void> removeFieldFromStaff(@RequestPart("staffId") String staffId, @RequestPart("fieldCode") String fieldCode) {
        staffService.removeFieldFromStaff(staffId, fieldCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/field/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getStaffByFieldCode(@PathVariable("fieldCode") String fieldCode) {
        return staffService.findStaffByFieldCode(fieldCode);
    }

}
