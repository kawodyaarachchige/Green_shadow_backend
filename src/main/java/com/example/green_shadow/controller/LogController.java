package com.example.green_shadow.controller;

import com.example.green_shadow.dto.impl.LogDTO;
import com.example.green_shadow.service.LogService;
import com.example.green_shadow.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.SimpleTimeZone;

@RestController
@RequestMapping("api/v1/logs")
@CrossOrigin
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> saveLog(@RequestBody LogDTO logDTO) {
        logService.saveLog(logDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> uploadImage(@RequestPart("logCode") String logCode, @RequestPart("image") MultipartFile image) throws IOException {
        byte [] imageBytes = image.getBytes();
        String imageString = AppUtil.convertImageToBase64(imageBytes);
        logService.uploadObservedImage(logCode, imageString);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public List<LogDTO> getLogs() {
        return logService.getAllLogs();
    }
    @DeleteMapping(value = "/{logCode}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> deleteLog(@PathVariable("logCode") String logCode) {
        logService.deleteLog(logCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/{logCode}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> updateLog(@PathVariable("logCode") String logCode, @RequestBody LogDTO logDTO) {
        logService.updateLog(logCode, logDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/date", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public List<LogDTO> getLogsBetweenDates(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return logService.getLogsBetweenDates(formatter.parse(startDate), formatter.parse(endDate));
    }
    @PostMapping(value = "/crop", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> createLogForCrop(@RequestPart("cropCode") String cropCode, @RequestPart("logCode") String logCode) {
        logService.createLogForCrop(cropCode, logCode);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/crop", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public ResponseEntity<Void> deleteLogForCrop(@RequestPart("cropCode") String cropCode, @RequestPart("logCode") String logCode) {
        logService.deleteLogForCrop(cropCode, logCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/crop/{cropCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    public List<LogDTO> getLogsForCrop(@PathVariable("cropCode") String cropCode) {
        return logService.findLogsByCropCode(cropCode);
    }
}
