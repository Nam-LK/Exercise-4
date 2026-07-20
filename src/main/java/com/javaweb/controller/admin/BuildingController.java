package com.javaweb.controller.admin;

import com.javaweb.enums.BuildingType;
import com.javaweb.enums.DistrictCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute("buildingSearch") BuildingSearchRequest buildingSearchRequest,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        //add enum và dữ liệu nhập vào
        mav.addObject("buildingType", BuildingType.type());
        mav.addObject("districtCode", DistrictCode.type());
        mav.addObject("listStaffs", userService.getStaffs());

        //trả kết quả trả về
        mav.addObject("buildingResponse",buildingService.searchBuildings(buildingSearchRequest));

        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buidlingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts", DistrictCode.type());
        mav.addObject("typeCodes", BuildingType.type());
        return mav;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView buidlingEdit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTOs = buildingService.getBuildingById(id);
        mav.addObject("buildingDTO", buildingDTOs);
        mav.addObject("districts", DistrictCode.type());
        mav.addObject("typeCodes", BuildingType.type());
        return mav;
    }
}