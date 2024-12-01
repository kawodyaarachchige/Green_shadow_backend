package com.example.green_shadow.service;

import com.example.green_shadow.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    String saveCrop (CropDTO cropDTO);
    List<CropDTO> getCrops ();
    void saveImage (String cropCode, String image);
    void updateCrop (CropDTO cropDTO);
    void deleteCrop (String cropCode);
    CropDTO findCrop (String cropCode);
    List<CropDTO> getCropByName (String name);
    void createLogForCrop (String cropCode, String logCode);
    void deleteLogForCrop (String cropCode, String logCode);
}