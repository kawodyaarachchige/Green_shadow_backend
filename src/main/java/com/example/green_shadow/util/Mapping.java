package com.example.green_shadow.util;

import com.example.green_shadow.dto.impl.*;
import com.example.green_shadow.entity.impl.Crop;
import com.example.green_shadow.entity.impl.Field;
import com.example.green_shadow.entity.impl.Log;
import com.example.green_shadow.entity.impl.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public User mapToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
    public UserDTO mapToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public Crop mapToCrop(CropDTO cropDTO) {return modelMapper.map(cropDTO, Crop.class); }
    public CropDTO mapToCropDTO(Crop crop) {return modelMapper.map(crop, CropDTO.class); }
    public List<CropDTO> mapToCropDTOList(List<Crop> crops) {return crops.stream().map(this::mapToCropDTO).toList(); }
    public Field mapToField(FieldDTO fieldDTO) {return modelMapper.map(fieldDTO, Field.class); }
    public FieldDTO mapToFieldDTO(Field field) {return modelMapper.map(field, FieldDTO.class); }
    public List<FieldDTO> mapToFieldDTOList(List<Field> fieldList) {
        return fieldList.stream().map(field -> {
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(field.getFieldCode());
            fieldDTO.setFieldName(field.getFieldName());
            fieldDTO.setFieldLocation(field.getFieldLocation());
            fieldDTO.setExtentSizeOfField(field.getExtentSizeOfField());
            fieldDTO.setImage1(field.getImage1());
            fieldDTO.setImage2(field.getImage2());
            fieldDTO.setCrops(
                    field.getCrops().stream().map(crop -> CropDTO.builder()
                            .cropCode(crop.getCropCode())
                            .cropCommonName(crop.getCropCommonName())
                            .cropScientificName(crop.getCropScientificName())
                            .cropImage(crop.getCropImage())
                            .category(crop.getCategory())
                            .cropSeason(crop.getCropSeason())
                            .fieldCode(crop.getField().getFieldCode())
                            .build()).toList()
            );
            fieldDTO.setStaff(
                    field.getStaff().stream().map(staff -> StaffDTO.builder()
                            .id(staff.getId())
                            .firstName(staff.getFirstName())
                            .lastName(staff.getLastName())
                            .joinedDate(staff.getJoinedDate())
                            .gender(staff.getGender())
                            .designation(staff.getDesignation())
                            .addressLine1(staff.getAddressLine1())
                            .addressLine2(staff.getAddressLine2())
                            .addressLine3(staff.getAddressLine3())
                            .addressLine4(staff.getAddressLine4())
                            .addressLine5(staff.getAddressLine5())
                            .contactNumber(staff.getContactNumber())
                            .email(staff.getEmail())
                            .role(staff.getRole())
                            .build()).toList()
            );
            fieldDTO.setEquipments(
                    field.getEquipments().stream().map(equipment -> EquipmentDTO.builder()
                            .equipmentId(equipment.getEquipmentId())
                            .name(equipment.getName())
                            .equipmentType(equipment.getEquipmentType())
                            .status(equipment.getStatus())
                            .staffId(equipment.getStaff().getId())
                            .fieldCode(equipment.getField().getFieldCode())
                            .build()).toList()
            );
            fieldDTO.setLogCode(field.getLog().getLogCode());
            return fieldDTO;
        }).toList();
    }
    public Log mapToLog(LogDTO logDTO) {return modelMapper.map(logDTO, Log.class); }
    public LogDTO mapToLogDTO(Log log) {return modelMapper.map(log, LogDTO.class); }

}
