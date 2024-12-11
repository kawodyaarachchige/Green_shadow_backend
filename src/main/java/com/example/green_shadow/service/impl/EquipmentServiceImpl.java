package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.EquipmentDAO;
import com.example.green_shadow.dto.impl.EquipmentDTO;
import com.example.green_shadow.entity.Status;
import com.example.green_shadow.entity.impl.Equipment;
import com.example.green_shadow.entity.impl.Field;
import com.example.green_shadow.exception.NoSuchEntityException;
import com.example.green_shadow.service.EquipmentService;
import com.example.green_shadow.service.FieldService;
import com.example.green_shadow.util.AppUtil;
import com.example.green_shadow.util.Mapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private StaffServiceImpl staffService;


    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        equipmentDTO.setEquipmentId(AppUtil.generateEquipmentId());
        equipmentDao.save(mapping.mapToEquipment(equipmentDTO));
        log.info("Equipment Saved :)" + equipmentDTO.getEquipmentId());
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return mapping.mapToEquipmentDTOList(equipmentDao.findAll());
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<Equipment> fetchedEquipment = equipmentDao.findById(equipmentId);
        Optional<Field> fieldByEquipmentId = equipmentDao.findFieldByEquipmentId(equipmentId);
        if (fetchedEquipment.isPresent()&& fieldByEquipmentId.isPresent()) {
            Equipment equipment = fetchedEquipment.get();
            equipment.setName(equipmentDTO.getName());
            equipment.setEquipmentType(equipmentDTO.getEquipmentType());
            equipment.setStatus(equipmentDTO.getStatus());
            equipment.setStaff(mapping.mapToStaff(staffService.findStaff(equipmentDTO.getStaffId())));
            equipment.setField(fieldByEquipmentId.get());
            equipmentDao.save(equipment);
            log.info("Equipment Updated :)" + equipmentId);}
        else {
            throw new NoSuchEntityException("Equipment", equipmentId);
        }
        }

    @Override
    public void deleteEquipment(String equipmentId) {
        equipmentDao.deleteById(equipmentId);
        log.warn("Equipment Deleted :)" + equipmentId);

    }

    @Override
    public EquipmentDTO findEquipment(String equipmentId) {
        return mapping.mapToEquipmentDTO(equipmentDao.findById(equipmentId).get());

    }

    @Override
    public List<EquipmentDTO> getEquipmentByName(String name) {
        return (mapping.mapToEquipmentDTOList(equipmentDao.findByName(name)));

    }

    @Override
    public List<EquipmentDTO> getEquipmentByStatus(Status status) {
        return (mapping.mapToEquipmentDTOList(equipmentDao.findByStatus(status)));

    }
}
