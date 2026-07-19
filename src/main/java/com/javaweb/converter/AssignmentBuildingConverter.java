package com.javaweb.converter;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignmentBuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AssignmentBuildingDTO toAssignmentBuildingDTO(AssignBuildingEntity assignBuildingEntity) {
        AssignmentBuildingDTO assignmentBuildingDTO = modelMapper.map(assignBuildingEntity, AssignmentBuildingDTO.class);
        return assignmentBuildingDTO;
    }

    public AssignBuildingEntity toAssignBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
        AssignBuildingEntity assignBuildingEntity = modelMapper.map(assignmentBuildingDTO, AssignBuildingEntity.class);
        return assignBuildingEntity;
    }
}
