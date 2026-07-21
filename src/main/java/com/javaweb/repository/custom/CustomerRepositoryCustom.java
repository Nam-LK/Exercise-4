package com.javaweb.repository.custom;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignCustomerDTO;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> searchCustomer(CustomerSearchBuilder customerSearchBuilder);
}
