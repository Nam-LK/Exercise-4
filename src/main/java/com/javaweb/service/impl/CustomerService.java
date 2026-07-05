package com.javaweb.service.impl;


import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerConverter;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.converter.CustomerSearchResConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssginCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseAssignDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private CustomerSearchBuilderConverter customerSearchBuilderConverter;
    private CustomerRepository customerRepository;
    private CustomerSearchResConverter customerSearchResConverter;
    private CustomerConverter customerConverter;

    private UserRepository userRepository;

    @Autowired
    public CustomerService(CustomerSearchBuilderConverter customerSearchBuilderConverter, CustomerRepository customerRepository, CustomerSearchResConverter converter, CustomerConverter customerConverter,
                           UserRepository userRepository) {
        this.customerSearchBuilderConverter = customerSearchBuilderConverter;
        this.customerRepository = customerRepository;
        this.customerSearchResConverter = converter;
        this.customerConverter = customerConverter;
        this.userRepository = userRepository;
    }

    @Override
    public List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest) {
        CustomerSearchBuilder customerSearchBuilder = customerSearchBuilderConverter.toCustomerSearchBuilder(customerSearchRequest);
        List<CustomerEntity> list = customerRepository.searchCustomers(customerSearchBuilder);
        List<CustomerSearchResponse> res = new ArrayList<>();
        for (CustomerEntity item : list) {
            CustomerSearchResponse customerSearchResponse = customerSearchResConverter.toCustomerSearchResponse(item);
            res.add(customerSearchResponse);
        }
        return res;
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        return customerConverter.toCustomerDTO(customerEntity);
    }

    @Override
    public void createOrUpdate(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.toCustomerEntity(customerDTO);
        if(customerDTO.getId()!=null){
            customerEntity.setCreatedBy(customerDTO.getModifiedBy());
            customerEntity.setCreatedDate(customerDTO.getModifiedDate());
        }
        customerEntity.setActive(true);
        customerRepository.save(customerEntity);
    }

    @Override
    public StaffResponseDTO listStaff(Long customerId) {
        List<UserEntity> userEntities = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();

        List<UserEntity> staffAssignCustomer = customerEntity.getUserEntityList();
        List<ResponseAssignDTO> list = new ArrayList<>();
        for(UserEntity x : userEntities){
            ResponseAssignDTO responseAssignDTO = new ResponseAssignDTO();
            if(staffAssignCustomer.contains(x)){
                responseAssignDTO.setChecked("checked");
            }else{
                responseAssignDTO.setChecked("");
            }
            responseAssignDTO.setFullName(x.getFullName());
            responseAssignDTO.setStaffId(x.getId());
            list.add(responseAssignDTO);
        }
        StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
        staffResponseDTO.setData(list);
        staffResponseDTO.setMessage("success");
        return staffResponseDTO;
    }

    @Override
    @Transactional
    public void deleteCustomer(List<Long> ids) {
            for (Long item : ids) {
                CustomerEntity customerEntity = customerRepository.findById(item).get();
                customerEntity.setActive(false);
                customerRepository.save(customerEntity);
            }
    }

    @Override
    public void updateAssignCustomer(AssginCustomerDTO assginCustomerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assginCustomerDTO.getCustomerId()).get();
        List<UserEntity> updateAssignStaffList = userRepository.findByIdIn(assginCustomerDTO.getStaffId());
        customerEntity.setUserEntityList(updateAssignStaffList);
        customerRepository.save(customerEntity);
    }

    @Override
    public int countTotalItems() {
        return customerRepository.countTotalItems();
    }

    @Override
    public Page<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        CustomerSearchBuilder customerSearchBuilder = customerSearchBuilderConverter.toCustomerSearchBuilder(customerSearchRequest);
        Page<CustomerEntity> customerEntityPage = customerRepository.searchCustomers(customerSearchBuilder,pageable);
        return customerEntityPage.map(customerSearchResConverter::toCustomerSearchResponse);
    }
}
