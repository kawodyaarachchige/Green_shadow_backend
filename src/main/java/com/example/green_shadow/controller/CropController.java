package com.example.green_shadow.controller;

import com.example.green_shadow.dto.impl.CropDTO;
import com.example.green_shadow.service.CropService;
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
@RequestMapping("api/v1/crops")
@CrossOrigin
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> saveCrop(@RequestBody CropDTO cropDTO) {
        cropService.saveCrop(cropDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getCrops() {
        return cropService.getCrops();
    }
    @PutMapping(value = "/{cropCode}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> updateCrop(@PathVariable("cropCode") String cropCode, @RequestBody CropDTO cropDTO) {
        cropDTO.setCropCode(cropCode);
        cropService.updateCrop(cropDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping(value = "/{cropCode}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode) {
        cropService.deleteCrop(cropCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping(value = "/log", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> createLogForCrop(@RequestPart("cropCode") String cropCode, @RequestPart("logCode") String logCode) {
        cropService.createLogForCrop(cropCode, logCode);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/log", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> deleteLogForCrop(@RequestPart("cropCode") String cropCode, @RequestPart("logCode") String logCode) {
        cropService.deleteLogForCrop(cropCode, logCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> uploadImage(@RequestPart("cropCode") String cropCode, @RequestPart("image") MultipartFile image) throws IOException {
        byte [] imageBytes = image.getBytes();
        String imageString = AppUtil.convertImageToBase64(imageBytes);
        cropService.saveImage(cropCode, imageString);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getCropByName(@PathVariable("name") String name) {
        return cropService.getCropByName(name);
    }




}
