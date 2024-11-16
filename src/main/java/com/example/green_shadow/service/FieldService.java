package com.example.green_shadow.service;

import com.example.green_shadow.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    List<FieldDTO> getFields();
    void updateField(String fieldCode, FieldDTO fieldDTO);
    void deleteField(String fieldCode);
    void uploadFieldImage(String fieldCode, String image1, String image2);
    FieldDTO findField(String fieldCode);
    List <FieldDTO> getFieldsByExtendSizeOfField(double size);
}
