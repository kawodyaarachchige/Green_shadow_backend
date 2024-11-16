package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.CropDAO;
import com.example.green_shadow.dao.LogDAO;
import com.example.green_shadow.dto.impl.LogDTO;
import com.example.green_shadow.entity.impl.Crop;
import com.example.green_shadow.entity.impl.Log;
import com.example.green_shadow.exception.NoSuchEntityException;
import com.example.green_shadow.service.LogService;
import com.example.green_shadow.util.AppUtil;
import com.example.green_shadow.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDAO logDAO;
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public String saveLog(LogDTO logDTO) {
        logDTO.setLogCode(AppUtil.generateLogCode());
        logDAO.save(mapping.mapToLog(logDTO));
        log.info("log saved :)" + logDTO.getLogCode());
        return logDTO.getLogCode();
    }

    @Override
    public void uploadObservedImage(String logCode, String image) {
        Optional<Log> fetchedLog = logDAO.findById(logCode);
        if (fetchedLog.isPresent()) {
            Log logs = fetchedLog.get();
            logs.setObservedImage(image);
            logDAO.save(logs);
            log.info("Image Saved :)" + logCode);
        } else {
            throw new NoSuchEntityException("Log", logCode);
        }

    }

    @Override
    public List<LogDTO> getAllLogs() {
        return mapping.mapToLogDTOList(logDAO.findAll());
    }

    @Override
    public void deleteLog(String logCode) {
        logDAO.deleteById(logCode);
        log.info("Log Deleted :)" + logCode);

    }

    @Override
    public void updateLog(String logCode, LogDTO logDTO) {
        Optional<Log> fetchedLog = logDAO.findById(logCode);
        if (fetchedLog.isPresent()) {
            Log logs = fetchedLog.get();
            logs.setLogDate(logDTO.getLogDate());
            logs.setLogDetails(logDTO.getLogDetails());
            logDAO.save(logs);
            log.info("Log Updated :)" + logCode);
        } else {
            throw new NoSuchEntityException("Log", logCode);
        }

    }

    @Override
    public LogDTO findLog(String logCode) {
        Optional<Log> fetchedLog = logDAO.findById(logCode);
        if (fetchedLog.isPresent()) {
            Log logs = fetchedLog.get();
            return mapping.mapToLogDTO(logs);
        } else {
            throw new NoSuchEntityException("Log", logCode);
        }
    }


    @Override
    public List<LogDTO> getLogsBetweenDates(Date startDate, Date endDate) {
        return mapping.mapToLogDTOList(logDAO.getLogsBetweenDates(startDate, endDate));
    }

    @Override
    public void createLogForCrop(String cropCode, String logCode) {
        Log logs = logDAO.findById(logCode).orElseThrow(() -> new NoSuchEntityException("Log", logCode));
        Crop crops = cropDAO.findById(cropCode).orElseThrow(() -> new NoSuchEntityException("Crop", cropCode));
        crops.addLog(logs);
        cropDAO.save(crops);
        log.info("Log Added to Crop :)" + cropCode);

    }

    @Override
    public void deleteLogForCrop(String cropCode, String logCode) {
        Log logs = logDAO.findById(logCode).orElseThrow(() -> new NoSuchEntityException("Log", logCode));
        Crop crops = cropDAO.findById(cropCode).orElseThrow(() -> new NoSuchEntityException("Crop", cropCode));
        crops.removeLog(logs);
        cropDAO.save(crops);
        log.info("Log Deleted from Crop :)" + cropCode);

    }

    @Override
    public List<LogDTO> findLogsByCropCode(String cropCode) {
        return mapping.mapToLogDTOList(logDAO.findLogsByCropCode(cropCode));
    }
}
