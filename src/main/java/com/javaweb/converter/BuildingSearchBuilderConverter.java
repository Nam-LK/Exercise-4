package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest){
        return new BuildingSearchBuilder.BuildingBuilder()
                .setName(buildingSearchRequest.getName())
                .setDistrictCode(buildingSearchRequest.getDistrict())
                .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .setRentPriceFrom(buildingSearchRequest.getRentPriceFrom())
                .setRentPriceTo(buildingSearchRequest.getRentPriceTo())
                .setManagerName(buildingSearchRequest.getManagerName())
                .setManagerPhone(buildingSearchRequest.getManagerPhone())
                .setFloorArea(buildingSearchRequest.getFloorArea())
                .setWard(buildingSearchRequest.getWard())
                .setStreet(buildingSearchRequest.getWard())
                .setLevel(buildingSearchRequest.getLevel())
                .setDirection(buildingSearchRequest.getDirection())
                .setRentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                .setRentAreaTo(buildingSearchRequest.getRentAreaTo())
                .setStaffId(buildingSearchRequest.getStaffId())
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .build();
    }
}
