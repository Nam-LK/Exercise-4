package com.javaweb.service;

import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;

import java.util.List;

public interface ICustomerService {
    List<CustomerSearchResponse> searchCustomer(CustomerSearchRequest searchRequest);
    void deleteCustomer(Long[] id);
    void addOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long id);
}
