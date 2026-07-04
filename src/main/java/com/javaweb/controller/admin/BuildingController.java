package com.javaweb.controller.admin;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/building/list");
        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buidlingEdit(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        return mav;
    }



}
