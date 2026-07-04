package com.javaweb.controller.admin;



import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {
    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute BuildingSearchRequest buildingSearchRequest,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
//        mav.setViewName("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        //làm tương tự như pj2 lấy repo ->service ->model

        //giả dữ liệu
        List<BuildingSearchResponse> responseList = new ArrayList<>();
        BuildingSearchResponse item1 = new BuildingSearchResponse();
        item1.setId(3L);
        item1.setName("ACM Building");
        item1.setAddress("130 Quang Trung, Phạm Ngũ Lão, Quận 1");
        item1.setNumberOfBasement(4L);
        item1.setManagerName("Anh Long");
        item1.setManagerPhone("0987654321");
        item1.setRentArea("100,200,300");
        responseList.add(item1);

        BuildingSearchResponse item2 = new BuildingSearchResponse();
        item2.setId(4L);
        item2.setName("Building Tower");
        item2.setAddress("130 Nguyễn Huệ, Phạm Ngũ Lão, Quận 4");
        item2.setNumberOfBasement(5L);
        item2.setManagerName("Anh Hải");
        item2.setManagerPhone("0900000001");
        item2.setRentArea("200,300");
        responseList.add(item2);
        mav.addObject("buildingList", responseList);
        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView buidlingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
//        mav.addObject("building", buildingDTO);
        return mav;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView buidlingEdit(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
//        xuống DB tìm building theo Id
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(id);
        buildingDTO.setName("Vô danh");
        mav.addObject("buildingEdit", buildingDTO);
        return mav;
    }
}
