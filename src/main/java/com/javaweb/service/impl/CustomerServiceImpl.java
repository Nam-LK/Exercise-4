package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerEditConverter;
import com.javaweb.converter.CustomerResponseConverter;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerResponseConverter customerResConverter;
    @Autowired
    private CustomerEditConverter customerEditConverter;

    public List<CustomerSearchResponse> searchCustomer(CustomerSearchRequest request) {
        CustomerSearchBuilder customerSearchBuilder = CustomerSearchBuilderConverter.toCustomerSearchBuilder(request);
        List<CustomerEntity> customerEntity = customerRepository.searchCustomer(customerSearchBuilder);

        List<CustomerSearchResponse> res = new ArrayList<>();
        for (CustomerEntity it : customerEntity) {
            CustomerSearchResponse customerSearchResponse = customerResConverter.toCustomerSearchResponse(it);
            res.add(customerSearchResponse);
        }
        return res;
    }

    @Override
    public void deleteCustomer(Long[] ids) {
        customerRepository.deleteByIdIn(ids);
    }

    @Override
    public void addOrUpdateCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerEditConverter.toCustomerEntity(customerDTO);
        customerRepository.addOrUpdateCustomer(customerEntity);
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        CustomerEntity customerEntity = customerRepository.getCustomer(id);
        CustomerDTO customerDTO = customerEditConverter.toCustomerDTO(customerEntity);
        return customerDTO;
    }
}
