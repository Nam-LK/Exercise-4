package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;

public interface AssignmentBuildingService {
    void deleteByBuildingsIn(Long[] ids);
    void addAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO);
}
