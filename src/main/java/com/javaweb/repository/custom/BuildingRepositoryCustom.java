package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder);
    void addOrUpdateBuilding(BuildingEntity buildingEntity);
    void updateAssignBuilding(AssignBuildingEntity assignBuildingEntity);
}
