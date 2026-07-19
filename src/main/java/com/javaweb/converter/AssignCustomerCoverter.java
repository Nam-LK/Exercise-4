package com.javaweb.converter;

import com.javaweb.entity.AssignCustomerEntity;
import com.javaweb.model.dto.AssignCustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignCustomerCoverter {
    @Autowired
    private ModelMapper modelMapper;

    public AssignCustomerEntity toAssignCustomerEntity(AssignCustomerDTO assignCustomerDTO) {
        AssignCustomerEntity assignCustomerEntity = modelMapper.map(assignCustomerDTO, AssignCustomerEntity.class);
        return assignCustomerEntity;
    }

    public AssignCustomerDTO toAssignCustomerDTO(AssignCustomerEntity assignCustomerEntity) {
        AssignCustomerDTO assignCustomerDTO = modelMapper.map(assignCustomerEntity, AssignCustomerDTO.class);
        return assignCustomerDTO;
    }
}
