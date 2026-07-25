package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.DistrictEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingSearchResponseConverter {
    @Autowired
    private ModelMapper modelMapper;

    //chuyển từ buildingentity sang buildingsearchresponse
    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        DistrictEntity districtEntity = buildingEntity.getDistrict();
        buildingSearchResponse.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtEntity.getName());

        List<RentAreaEntity> list = buildingEntity.getRentAreas();
        String rentAreaResult = list.stream().map(RentAreaEntity::getValue).collect(Collectors.joining(","));
        buildingSearchResponse.setRentArea(rentAreaResult);
        return buildingSearchResponse;
    }
}
