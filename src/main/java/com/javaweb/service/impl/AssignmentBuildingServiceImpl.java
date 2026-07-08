package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteByBuildingsIn(Long[] ids) {
        for(Long it : ids) {
            BuildingEntity buildingEntity = buildingRepository.findById(it).get();
            assignmentBuildingRepository.deleteByBuildings(buildingEntity);
        }
//        assignmentBuildingRepository.deleteByBuildingsIdIn(buildingEntity);
    }

    @Override
    public void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        assignmentBuildingRepository.deleteByBuildings(buildingEntity);

        List<Long> staffIds = assignmentBuildingDTO.getStaffs();
        for(Long it : staffIds) {
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setBuildings(buildingEntity);

            UserEntity userEntity = userRepository.findById(it).get();
            assignmentBuildingEntity.setStaffs(userEntity);

            assignmentBuildingRepository.save(assignmentBuildingEntity);
        }
    }
}
