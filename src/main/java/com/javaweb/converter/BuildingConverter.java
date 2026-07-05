package com.javaweb.converter;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    private ModelMapper modelMapper;

    @Autowired
    public BuildingConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<RentAreaEntity> rentAreaEntityList = buildingEntity.getRentAreaEntities();
        if (rentAreaEntityList != null && !rentAreaEntityList.isEmpty()) {
            buildingDTO.setRentArea(
                    rentAreaEntityList.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","))
            );
        }
        return buildingDTO;
    }

    public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        List<RentAreaEntity> rentAreaEntities = Arrays.stream(buildingDTO.getRentArea().split(","))
                .map(value -> {
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();
                    rentAreaEntity.setValue(Integer.parseInt(value));
                    rentAreaEntity.setBuildingEntity(buildingEntity);
                    return rentAreaEntity;
                })
                .collect(Collectors.toList());
        buildingEntity.setRentAreaEntities(rentAreaEntities);
        return buildingEntity;
    }
}
