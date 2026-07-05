package com.javaweb.api.admin;

import com.javaweb.model.dto.AssginCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.model.response.TransactionResponseDTO;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {
    private ICustomerService iCustomerService;
    private ITransactionService iTransactionService;

    @Autowired
    public CustomerAPI(ICustomerService iCustomerService,ITransactionService iTransactionService){
        this.iCustomerService = iCustomerService;
        this.iTransactionService = iTransactionService;
    }

    @GetMapping
    public List<CustomerSearchResponse> searchCustomers(@RequestBody CustomerSearchRequest customerSearchRequest){
        return iCustomerService.searchCustomers(customerSearchRequest);
    }

    @PostMapping("/transaction")
    public void createTransaction(@RequestBody TransactionDTO transactionDTO){
        iTransactionService.addOrUpdateTransaction(transactionDTO);
    }

//    @PutMapping("/transaction/{id}")
//    public void updateTransaction(@RequestBody TransactionDTO transactionDTO, @PathVariable Long id){
//        iTransactionService.addOrUpdateTransaction(transactionDTO);
//    }

    @PostMapping
    public void createCustomer(@RequestBody CustomerDTO customerDTO){
        iCustomerService.createOrUpdate(customerDTO);
    }

    @PutMapping({"/{customerId}"})
    public void updateCustomer(@RequestBody CustomerDTO customerDTO,@PathVariable Long customerId){
        iCustomerService.createOrUpdate(customerDTO);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestBody List<Long> ids){
        if(ids.size()>0){
            iCustomerService.deleteCustomer(ids);
        }
    }

    @GetMapping("/{customerId}/staffs")
    public StaffResponseDTO loadStaff(@PathVariable Long customerId){
        return iCustomerService.listStaff(customerId);
    }

    @PostMapping("/assignment")
    public void updateAssignCustomer(@RequestBody AssginCustomerDTO assginCustomerDTO){
        iCustomerService.updateAssignCustomer(assginCustomerDTO);
    }

    @GetMapping("/{transactionId}/details")
    public TransactionResponseDTO loadTransactionDetail(@PathVariable Long transactionId){
        return iTransactionService.loadTransactionDetail(transactionId);
    }
}