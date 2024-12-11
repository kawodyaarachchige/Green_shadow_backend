package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.CropDAO;
import com.example.green_shadow.dao.LogDAO;
import com.example.green_shadow.dto.impl.CropDTO;
import com.example.green_shadow.entity.impl.Crop;
import com.example.green_shadow.entity.impl.Log;
import com.example.green_shadow.exception.NoSuchEntityException;
import com.example.green_shadow.service.CropService;
import com.example.green_shadow.service.FieldService;
import com.example.green_shadow.util.AppUtil;
import com.example.green_shadow.util.Mapping;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private LogDAO logDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private FieldService fieldService;
    @Override
    public String saveCrop(CropDTO cropDTO) {
        cropDTO.setCropCode(AppUtil.generateCropCode(cropDTO.getCategory()));
        cropDAO.save(mapping.mapToCrop(cropDTO));
        log.info("Crop Saved :)" + cropDTO.getCropCode());
        return cropDTO.getCropCode();
    }

    @Override
    public List<CropDTO> getCrops() {
        return mapping.mapToCropDTOList(cropDAO.findAll());
    }

    @Override
    public void saveImage(String cropCode, String image) {
        Optional<Crop> fetchedCrop = cropDAO.findById(cropCode);
        if (fetchedCrop.isPresent()) {
            Crop crop = fetchedCrop.get();
            if (image != null) {
                crop.setCropImage(image);
                cropDAO.save(crop);
                log.info("Image Saved :)" + cropCode);
            }
        }
        else {
            throw new NoSuchEntityException("Crop", cropCode);
        }

    }

    @Override
    public void updateCrop(CropDTO cropDTO) {
        Optional<Crop> fetchedCrop = cropDAO.findById(cropDTO.getCropCode());
        if (fetchedCrop.isPresent()) {
            Crop crop = fetchedCrop.get();
            crop.setCropCommonName(cropDTO.getCropCommonName());
            crop.setCropScientificName(cropDTO.getCropScientificName());
            crop.setCategory(cropDTO.getCategory());
            crop.setCropSeason(cropDTO.getCropSeason());
            cropDAO.save(crop);
            log.info("Crop Updated :)" + cropDTO.getCropCode());
        }
        else {
            throw new NoSuchEntityException("Crop", cropDTO.getCropCode());
        }

    }

    @Override
    public void deleteCrop(String cropCode) {
        cropDAO.deleteByCropCode(cropCode);
        log.info("Crop Deleted :)" + cropCode);

    }

    @Override
    public CropDTO findCrop(String cropCode) {
        Optional<Crop> fetchedCrop = cropDAO.findById(cropCode);
        if (fetchedCrop.isPresent()) {
            Crop crop = fetchedCrop.get();
            log.info("Crop Found :)" + cropCode);
            return mapping.mapToCropDTO(crop);
        } else {
            throw new NoSuchEntityException("Crop", cropCode);
        }
    }

    @Override
    public List<CropDTO> getCropByName(String name) {
        return mapping.mapToCropDTOList(cropDAO.findByName(name));
    }

    @Override
    public void createLogForCrop(String cropCode, String logCode) {
        Crop fetchedCrop = cropDAO.findById(cropCode).orElseThrow(() -> new NoSuchEntityException("Crop", cropCode));
        Log fetchedLog = logDAO.findById(logCode).orElseThrow(() -> new NoSuchEntityException("Log", logCode));
        fetchedCrop.addLog(fetchedLog);
        cropDAO.save(fetchedCrop);
        log.info("Log Added to Crop :)" + cropCode);

    }

    @Override
    public void deleteLogForCrop(String cropCode, String logCode) {
        Crop fetchedCrop = cropDAO.findById(cropCode).orElseThrow(() -> new NoSuchEntityException("Crop", cropCode));
        Log fetchedLog = logDAO.findById(logCode).orElseThrow(() -> new NoSuchEntityException("Log", logCode));
        fetchedCrop.removeLog(fetchedLog);
        cropDAO.save(fetchedCrop);
        log.info("Log Deleted from Crop :)" + cropCode);

    }
}
