package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RentAreaServiceImpl implements RentAreaService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Override
    public void deleteByBuilding(Long[] ids) {
        for (Long id : ids) {
            BuildingEntity buildingEntity = buildingRepository.findById(Long.valueOf(id)).get();
            rentAreaRepository.deleteByBuilding(buildingEntity);
        }
        //        rentAreaRepository.deleteByBuildingIdIn(ids);
    }

    @Override
    public void addRentArea(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingDTO.getId()).get();
        rentAreaRepository.deleteByBuildingId(buildingEntity);

        String[] rentAreas = buildingDTO.getRentArea().split(",");

        for (String val : rentAreas) {

            RentAreaEntity rentAreaEntity = rentAreaConverter.toRentAreaEntity(buildingDTO, Long.value0f(val));
            rentAreaRepository.save(rentAreaEntity);
        }
    }
}
