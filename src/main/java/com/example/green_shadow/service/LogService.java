package com.example.green_shadow.service;

import com.example.green_shadow.dto.impl.LogDTO;

import java.util.Date;
import java.util.List;

public interface LogService {
    String saveLog(LogDTO logDTO);
    void uploadObservedImage(String logCode, String image);
    List<LogDTO> getAllLogs();
    void deleteLog(String logCode);
    void updateLog(String logCode, LogDTO logDTO);
    LogDTO findLog(String logCode);
    List <LogDTO> getLogsBetweenDates(Date startDate, Date endDate);
    void createLogForCrop(String cropCode, String logCode);
    void deleteLogForCrop(String cropCode, String logCode);
    List <LogDTO> findLogsByCropCode(String cropCode);
}
