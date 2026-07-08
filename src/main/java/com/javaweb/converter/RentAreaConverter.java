package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.RentAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentAreaConverter {
    @Autowired
    private RentAreaRepository rentAreaRepository;

    public RentAreaEntity toRentAreaEntity(BuildingEntity buildingEntity, Long val) {
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        rentAreaEntity.setValue(val);
        rentAreaEntity.setBuildingId(buildingEntity);
        return rentAreaEntity;
    }

    public List<RentAreaEntity> toRentAreaEntityList(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String [] rentAreas = buildingDTO.getRentArea().split(",");
        List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();

        for (String rentArea : rentAreas) {
            rentAreaEntityList.add(toRentAreaEntity(buildingEntity, Long.valueOf(rentArea)));
        }
        return rentAreaEntityList;
    }
}
