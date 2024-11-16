package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.CropDAO;
import com.example.green_shadow.dao.LogDAO;
import com.example.green_shadow.dto.impl.LogDTO;
import com.example.green_shadow.service.LogService;
import com.example.green_shadow.util.AppUtil;
import com.example.green_shadow.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    }

    @Override
    public List<LogDTO> getAllLogs() {
        return List.of();
    }

    @Override
    public void deleteLog(String logCode) {

    }

    @Override
    public void updateLog(String logCode, LogDTO logDTO) {

    }

    @Override
    public LogDTO findLog(String logCode) {
        return null;
    }

    @Override
    public List<LogDTO> getLogsBetweenDates(Date startDate, Date endDate) {
        return List.of();
    }

    @Override
    public void createLogForCrop(String cropCode, String logCode) {

    }

    @Override
    public void deleteLogForCrop(String cropCode, String logCode) {

    }

    @Override
    public List<LogDTO> findLogsByCropCode(String cropCode) {
        return List.of();
    }
}
