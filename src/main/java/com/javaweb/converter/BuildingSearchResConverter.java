package com.javaweb.converter;

import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.DistrictCode;
import com.javaweb.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class BuildingSearchResConverter {
    private ModelMapper modelMapper;
    private RentAreaRepository rentAreaRepository;

    @Autowired
    public BuildingSearchResConverter(ModelMapper modelMapper,RentAreaRepository rentAreaRepository){
        this.modelMapper = modelMapper;
        this.rentAreaRepository = rentAreaRepository;
    }
    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity buildingEntity){
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity,BuildingSearchResponse.class);
        Map<String,String> districtList = DistrictCode.getDistrict();
        String districtName ="";
        for(Map.Entry<String,String> it : districtList.entrySet()){
            if(it.getKey().equals(buildingEntity.getDistrict()))
                districtName = it.getValue();
        }
        List<RentAreaEntity> rentAreaEntityList = buildingEntity.getRentAreaEntities();
//        String address = buildingEntity.getStreet()+","+buildingEntity.getWard()+","+districtName;
        String address = String.join(",",buildingEntity.getStreet(),buildingEntity.getWard(),districtName);
        buildingSearchResponse.setAddress(address);
        buildingSearchResponse.setRentArea(
                rentAreaEntityList.stream()
                        .map(it -> it.getValue().toString())
                        .collect(Collectors.joining(","))
        );
        return buildingSearchResponse;
    }
}
