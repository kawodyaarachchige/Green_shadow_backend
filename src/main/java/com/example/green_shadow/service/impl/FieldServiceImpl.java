package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.FieldDAO;
import com.example.green_shadow.dto.impl.FieldDTO;
import com.example.green_shadow.entity.impl.Field;
import com.example.green_shadow.exception.NoSuchEntityException;
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
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private LogServiceImpl logService;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setFieldCode(AppUtil.generateFieldCode());
        fieldDAO.save(mapping.mapToField(fieldDTO));
        log.info("Field Saved :)" + fieldDTO.getFieldCode());
    }

    @Override
    public List<FieldDTO> getFields() {
        return mapping.mapToFieldDTOList(fieldDAO.findAll());
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<Field> fetchedField = fieldDAO.findById(fieldCode);
        if (fetchedField.isPresent()) {
            Field field = fetchedField.get();
            field.setFieldName(fieldDTO.getFieldName());
            field.setFieldLocation(fieldDTO.getFieldLocation());
            field.setExtentSizeOfField(fieldDTO.getExtentSizeOfField());
            field.setLog(mapping.mapToLog(logService.findLog(fieldDTO.getLogCode())));
            fieldDAO.save(field);
            log.info("Field Updated :)" + fieldCode);
        } else {
            throw new NoSuchEntityException("Field", fieldCode);
        }

    }

    @Override
    public void deleteField(String fieldCode) {
        fieldDAO.deleteById(fieldCode);
        log.info("Field Deleted :)" + fieldCode);

    }

    @Override
    public void uploadFieldImage(String fieldCode, String image1, String image2) {
        Optional<Field> fetchedField = fieldDAO.findById(fieldCode);
        if (fetchedField.isPresent()) {
            Field field = fetchedField.get();
            if (image1 != null) {
                field.setImage1(field.getImage1());
            } else {
                field.setImage1(image1);
            }
            if (image2 != null) {
                field.setImage2(field.getImage2());
            } else {
                field.setImage2(image2);
            }
            fieldDAO.save(field);
            log.info("Field Image Updated :)" + fieldCode);
        } else {
            throw new NoSuchEntityException("Field", fieldCode);
        }

    }

    @Override
    public FieldDTO findField(String fieldCode) {
        Optional<Field> fetchedField = fieldDAO.findById(fieldCode);
        if (fetchedField.isPresent()) {
            Field field = fetchedField.get();
            return mapping.mapToFieldDTO(field);
        }
        throw new NoSuchEntityException("Field", fieldCode);
    }

    @Override
    public List<FieldDTO> getFieldsByExtendSizeOfField(double size) {
        return mapping.mapToFieldDTOList(fieldDAO.findByExtentSizeOfField(size));
    }
}
