package com.javaweb.service;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.TypeCodeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> searchBuilding(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO findBuildingById(Long id);
    void createOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuilding(List<Long> ids);

    Page<BuildingSearchResponse> getBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    int countTotalItem();
    TypeCodeResponseDTO listTypeCode(Long buildingId);
}
