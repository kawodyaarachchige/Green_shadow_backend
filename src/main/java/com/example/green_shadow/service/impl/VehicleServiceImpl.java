package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.VehicleDAO;
import com.example.green_shadow.dto.impl.VehicleDTO;
import com.example.green_shadow.entity.Status;
import com.example.green_shadow.entity.impl.Vehicle;
import com.example.green_shadow.exception.NoSuchEntityException;
import com.example.green_shadow.service.VehicleService;
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
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDao;
    @Autowired
    private Mapping mapper;
    @Autowired
    private StaffServiceImpl staffService;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleCode(AppUtil.generateVehicleCode());
        Vehicle vehicle = mapper.mapToVehicle(vehicleDTO);
        vehicle.setStaff(mapper.mapToStaff(staffService.findStaff(vehicleDTO.getStaff())));
        vehicleDao.save(vehicle);


    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapper.mapToVehicleDTOList(vehicleDao.findAll());

    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        Optional<Vehicle> fetchedVehicle = vehicleDao.findById(vehicleCode);
        if (fetchedVehicle.isPresent()) {
            Vehicle vehicle = fetchedVehicle.get();
            vehicle.setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            vehicle.setVehicleCategory(vehicleDTO.getVehicleCategory());
            vehicle.setFuelType(vehicleDTO.getFuelType());
            vehicle.setStatus(vehicleDTO.getStatus());
            vehicle.setRemarks(vehicleDTO.getRemarks());
            vehicle.setStaff(mapper.mapToStaff(staffService.findStaff(vehicleDTO.getStaff())));
            vehicleDao.save(vehicle);
            log.info("Vehicle updated successfully with code: {}", vehicleCode);
        } else {
            throw new NoSuchEntityException("Vehicle", vehicleCode);
        }

    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        vehicleDao.deleteById(vehicleCode);
        log.warn("Vehicle deleted successfully with code: {}", vehicleCode);
    }

    @Override
    public VehicleDTO findVehicle(String vehicleCode) {
        Optional<Vehicle> fetchedVehicle = vehicleDao.findById(vehicleCode);
        if (fetchedVehicle.isPresent()) {
            log.info("Vehicle found successfully with code: {}", vehicleCode);
            return mapper.mapToVehicleDTO(fetchedVehicle.get());
        } else {
            throw new NoSuchEntityException("Vehicle", vehicleCode);
        }

    }

    @Override
    public List<VehicleDTO> sortByVehicleCategory(String name) {
        return mapper.mapToVehicleDTOList(vehicleDao.sortByVehicleCategory(name));
    }


    @Override
    public List<VehicleDTO> findByStatus(Status status) {
        return mapper.mapToVehicleDTOList(vehicleDao.findByStatus(status));
    }

}
