package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.DistrictCode;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.javaweb.service.IBuildingService;
import com.javaweb.model.dto.BuildingDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    private IUserService iUserService;
    private IBuildingService iBuildingService;
    private MessageUtils messageUtils;
    @Autowired
    public BuildingController(IUserService iUserService, IBuildingService iBuildingService,MessageUtils messageUtils){
        this.iUserService = iUserService;
        this.iBuildingService = iBuildingService;
        this.messageUtils = messageUtils;
    }
    @RequestMapping(value = "/admin/building-list",method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("buildingSearch") BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("districts", DistrictCode.getDistrict());
        modelAndView.addObject("typeCodes", TypeCode.getRentType());
        modelAndView.addObject("staffs",iUserService.getListStaff());
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
            modelAndView.addObject("buildings",iBuildingService.searchBuilding(buildingSearchRequest));
        }else{
            modelAndView.addObject("buildings",iBuildingService.searchBuilding(buildingSearchRequest));
        }
        DisplayTagUtils.of(request,buildingSearchRequest);
        Page<BuildingSearchResponse> buildingSearchResponsePage = iBuildingService.getBuildings(buildingSearchRequest, PageRequest.of(buildingSearchRequest.getPage()-1,buildingSearchRequest.getMaxPageItems()));
        buildingSearchRequest.setTotalItems(iBuildingService.countTotalItem());
        buildingSearchRequest.setListResult(buildingSearchResponsePage.getContent());
        initMessageResponse(modelAndView,request);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/building-edit",method = RequestMethod.GET)
    public ModelAndView addBuilding(@ModelAttribute("buildingEdit")BuildingDTO buildingDTO){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("districts", DistrictCode.getDistrict());
        modelAndView.addObject("typeCodes", TypeCode.getRentType());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView updateBuilding(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = iBuildingService.findBuildingById(id);
        modelAndView.addObject("districts", DistrictCode.getDistrict());
        modelAndView.addObject("typeCodes", TypeCode.getRentType());
        modelAndView.addObject("buildingEdit",buildingDTO);
        return  modelAndView;
    }
    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }

}