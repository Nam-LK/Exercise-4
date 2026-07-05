package com.javaweb.controller.admin;

import com.javaweb.enums.StatusCode;
import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "customerMangementController")
public class CustomerController {
    private IUserService iUserService;
    private ICustomerService iCustomerService;
    private ITransactionService transactionService;
    @Autowired
    public CustomerController(IUserService iUserService, ICustomerService iCustomerService,ITransactionService transactionService){
        this.iUserService = iUserService;
        this.iCustomerService = iCustomerService;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/admin/customer-list",method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute("customerSearch")CustomerSearchRequest customerSearchRequest, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/customer/list");
        modelAndView.addObject("staffs",iUserService.getListStaff());
        DisplayTagUtils.of(request,customerSearchRequest);
        customerSearchRequest.setMaxPageItems(3);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
            modelAndView.addObject("customers",iCustomerService.searchCustomers(customerSearchRequest));
        }else{
            modelAndView.addObject("customers",iCustomerService.searchCustomers(customerSearchRequest));
        }
//        customerSearchRequest.setLimit(3);
        Page<CustomerSearchResponse> customerSearchResponsePage = iCustomerService.searchCustomers(customerSearchRequest, PageRequest.of(customerSearchRequest.getPage()-1,customerSearchRequest.getMaxPageItems()));
        customerSearchRequest.setTotalItems(iCustomerService.searchCustomers(customerSearchRequest).size());
        customerSearchRequest.setListResult(customerSearchResponsePage.getContent());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/customer-edit",method = RequestMethod.GET)
    public ModelAndView addCustomer(@ModelAttribute("customerEdit")CustomerDTO customerDTO){
        ModelAndView modelAndView = new ModelAndView("/admin/customer/edit");
        modelAndView.addObject("statusCode", StatusCode.getStatusCode());
        return modelAndView;
    }

    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView updateCustomer(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/admin/customer/edit");
        modelAndView.addObject("statusCode", StatusCode.getStatusCode());
        CustomerDTO customerDTO = iCustomerService.findById(id);
        modelAndView.addObject("customerEdit",customerDTO);
        modelAndView.addObject("transactionType", TransactionType.getTransactionType());
        modelAndView.addObject("CSKHList",transactionService.findByCodeAndCustomerId("CSKH",id));
        modelAndView.addObject("DDXList",transactionService.findByCodeAndCustomerId("DDX",id));
        return modelAndView;
    }
}