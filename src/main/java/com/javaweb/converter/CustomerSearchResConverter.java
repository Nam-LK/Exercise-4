package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.response.CustomerSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerSearchResConverter {
    private ModelMapper modelMapper;
    @Autowired
    public CustomerSearchResConverter(ModelMapper modelMapper){
            this.modelMapper = modelMapper;
    }
    public CustomerSearchResponse toCustomerSearchResponse(CustomerEntity customerEntity){
        CustomerSearchResponse customerSearchResponse = modelMapper.map(customerEntity,CustomerSearchResponse.class);
        return customerSearchResponse;
    }
}
