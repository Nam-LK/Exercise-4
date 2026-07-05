package com.javaweb.service;

import com.javaweb.model.dto.AssginCustomerDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest);

    CustomerDTO findById(Long id);

    void createOrUpdate(CustomerDTO customerDTO);

    StaffResponseDTO listStaff(Long customerId);

    void deleteCustomer(List<Long> ids);

    void updateAssignCustomer(AssginCustomerDTO assginCustomerDTO);

    int countTotalItems();
    Page<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest, Pageable pageable);
}
