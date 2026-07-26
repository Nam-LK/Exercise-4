package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerEditConverter;
import com.javaweb.converter.CustomerResponseConverter;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.entity.AssignCustomerEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignCustomerRepository;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignCustomerRepository assignCustomerRepository;

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
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        CustomerDTO customerDTO = customerEditConverter.toCustomerDTO(customerEntity);
        return customerDTO;
    }

    @Override
    public ResponseDTO listStaffs(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        List<AssignCustomerEntity> res1 = customer.getAssignCustomers();
        List<UserEntity> staffAssigment = new ArrayList<>();
        for(AssignCustomerEntity assignCustomerEntity : res1) {
            staffAssigment.add(assignCustomerEntity.getStaff());
        }

        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity staff : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(staff.getFullName());
            staffResponseDTO.setStaffId(staff.getId());
            if (staffAssigment.contains(staff)) {
                staffResponseDTO.setChecked("checked");
            }else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOList);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public void updateAssignCustomer(AssignCustomerDTO assignCustomerDTO) {
        List<AssignCustomerEntity> assignCustomerEntities = assignCustomerRepository.findByCustomerId(assignCustomerDTO.getCustomerId());
        assignCustomerEntities.clear();
        CustomerEntity customer = customerRepository.findById(assignCustomerDTO.getCustomerId()).get();

        List<Long> staffIds = assignCustomerDTO.getStaffs();
        List<UserEntity> staffAssigment = userRepository.findByIdIn(staffIds);
        for(UserEntity staff : staffAssigment) {
            if (!assignCustomerEntities.contains(staff)) {
                AssignCustomerEntity assignCustomerEntity = new AssignCustomerEntity();
                assignCustomerEntity.setStaff(staff);
                assignCustomerEntity.setCustomer(customer);
            }
        }
        customer.setAssignCustomers(assignCustomerEntities);
        customerRepository.save(customer);
    }
}
