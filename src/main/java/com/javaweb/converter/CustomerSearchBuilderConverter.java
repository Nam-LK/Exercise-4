package com.javaweb.converter;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

@Component
public class CustomerSearchBuilderConverter {
    public static CustomerSearchBuilder toCustomerSearchBuilder(CustomerSearchRequest request) {
        return new CustomerSearchBuilder.Builder()
                .setCustomerName(MapUtils.getObject(request.getCustomerName(), String.class))
                .setEmail(MapUtils.getObject(request.getEmail(), String.class))
                .setStaffId(MapUtils.getObject(request.getStaffId(),Long.class))
                .setPhoneNumber(MapUtils.getObject(request.getPhoneNumber(), String.class))
                .build();
    }
}
