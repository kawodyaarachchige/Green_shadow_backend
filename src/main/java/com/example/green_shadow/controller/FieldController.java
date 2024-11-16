package com.example.green_shadow.controller;

import com.example.green_shadow.dto.impl.FieldDTO;
import com.example.green_shadow.service.FieldService;
import com.example.green_shadow.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
@CrossOrigin
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> saveField(@RequestBody FieldDTO fieldDTO) {
        fieldService.saveField(fieldDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getFields() {
        return fieldService.getFields();
    }
    @PutMapping(value = "/{fieldCode}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> updateField(@PathVariable("fieldCode") String fieldCode, @RequestBody FieldDTO fieldDTO) {
        fieldService.updateField(fieldCode, fieldDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{fieldCode}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        fieldService.deleteField(fieldCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> uploadImage(@RequestPart("fieldCode") String fieldCode, @RequestPart("image1") MultipartFile image1,@RequestPart("image2") MultipartFile image2) throws IOException {
        byte [] image1Bytes = image1.getBytes();
        byte [] image2Bytes = image2.getBytes();
        fieldService.uploadFieldImage(fieldCode, AppUtil.convertImageToBase64(image1Bytes), AppUtil.convertImageToBase64(image2Bytes));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/{extentSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getFieldsByExtentSizeOfField(@PathVariable("extentSize") double extentSize) {
        return fieldService.getFieldsByExtendSizeOfField(extentSize);
    }



}
