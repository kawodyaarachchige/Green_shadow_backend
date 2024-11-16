package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.FieldDAO;
import com.example.green_shadow.dto.impl.FieldDTO;
import com.example.green_shadow.entity.impl.Field;
import com.example.green_shadow.exception.NoSuchEntityException;
import com.example.green_shadow.service.FieldService;
import com.example.green_shadow.util.AppUtil;
import com.example.green_shadow.util.Mapping;
import jakarta.transaction.Transactional;
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

    }

    @Override
    public void deleteField(String fieldCode) {

    }

    @Override
    public void uploadFieldImage(String fieldCode, String image1, String image2) {

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
        return List.of();
    }
}
