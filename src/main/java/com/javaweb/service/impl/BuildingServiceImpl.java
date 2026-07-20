package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingEditConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResponseConverter;
import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingSearchResponseConverter buildingSearchResponseConverter;

    @Autowired
    private BuildingEditConverter buildingEditConverter;

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
//        List<UserEntity> staffAssigment = building.getUserEntities();
        List<AssignBuildingEntity> res1 = building.getAssignBuildingEntityList();
        List<UserEntity> staffAssigment = new ArrayList<>();
        for(AssignBuildingEntity assignBuildingEntity : res1) {
            staffAssigment.add(assignBuildingEntity.getUserEntity());
        }

        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity staff : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(staff.getFullName());
            staffResponseDTO.setStaffId(staff.getId());
            if (staffAssigment.contains(staff)) {
                staffResponseDTO.setChecked("checked");
            }else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOList);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = BuildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> searchResult = buildingRepository.searchBuilding(buildingSearchBuilder);
        List<BuildingSearchResponse> res = new ArrayList<>();
        for (BuildingEntity building : searchResult) {
            BuildingSearchResponse buildingSearchResponse = buildingSearchResponseConverter.toBuildingSearchResponse(building);
            res.add(buildingSearchResponse);
        }
        return res;
    }

    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingEditConverter.toBuildingEntity(buildingDTO);
        buildingRepository.addOrUpdateBuilding(buildingEntity);
    }

    @Override
    public void deleteBuilding(Long[] ids) {
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public BuildingDTO getBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingEditConverter.toBuildingDTO(buildingEntity);
        return buildingDTO;
    }
}
