package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder);
    int countTotalItem();
    Page<BuildingEntity> getPageBuildings(BuildingSearchBuilder builder, Pageable pageable);
}
