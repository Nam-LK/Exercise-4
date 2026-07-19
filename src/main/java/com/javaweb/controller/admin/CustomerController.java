package com.javaweb.controller.admin;

import com.javaweb.enums.TransactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = "/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute("customerSearch")CustomerSearchRequest customerSearchRequest,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("listStaffs", userService.getStaffs());

        //trả kết quả trả về
        mav.addObject("customerResponse", customerService.searchCustomer(customerSearchRequest));
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit")
    public ModelAndView customerEdit(@ModelAttribute("customerEdit")CustomerDTO customerDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        return mav;
    }

    @GetMapping(value = "/admin/customer-edit-{id}")
    public ModelAndView customerEdit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        CustomerDTO customerDTO = customerService.getCustomer(id);
        mav.addObject("cusEdit", customerDTO);
        mav.addObject("transactionType", TransactionType.transactionType());
        return mav;
    }
}
