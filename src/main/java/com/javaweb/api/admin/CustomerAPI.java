package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "customerAPIOfAdmin")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/amdin/customer-edit")
    public void addCustomer(@RequestBody CustomerDTO customerDTO){ //thêm mới tòa nhà
        customerService.addOrUpdateCustomer(customerDTO);
    }

    @PostMapping("/amdin/customer-edit-{id}")
    public void updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id){ //sửa tòa nhà đã có id
        customerService.addOrUpdateCustomer(customerDTO);
    }

    @DeleteMapping("/api/customer/{id}")
    public void deleteCustomer(@PathVariable Long[] ids){
        if (ids.length > 0){
            customerService.deleteCustomer(ids);
        }
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadAllStaffs(@PathVariable Long id){
        ResponseDTO result = customerService.listStaffs(id);
        return result;
    }

    @PostMapping("/assignment")
    public void updateAssignment(@RequestBody AssignCustomerDTO assignCustomerDTO){
        customerService.updateAssignCustomer(assignCustomerDTO);
    }

    @PostMapping("/{cusId}/transaction")
    public void createTransaction(@PathVariable Long cusId, @RequestBody TransactionDTO transactionDTO){
        transactionService.createOrUpdateTransaction(cusId, transactionDTO);
    }

    @PostMapping("/{cusId}/{transactionId}/transaction")
    public void updateTransaction(@PathVariable Long cusId, @PathVariable Long transactionId, @RequestBody TransactionDTO transactionDTO){
        transactionService.createOrUpdateTransaction(cusId, transactionDTO);
    }
}
