package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IUserService userService;


    @PostMapping("/amdin/building-edit")
    public void addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){ //thêm mới tòa nhà
        buildingService.addOrUpdateBuilding(buildingDTO);
    }

    @PostMapping("/amdin/building-edit-{id}")
    public void addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO, @PathVariable Long id){ //sửa tòa nhà đã có id
        buildingService.addOrUpdateBuilding(buildingDTO);
    }

    @DeleteMapping("/api/building/{id}")
    public void deleteBuilding(@PathVariable Long[] ids){
        buildingService.deleteBuilding(ids);
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadAllStaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
        
    }

    @PostMapping("/assignment")
    public void updateAssignment(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        userService.updateAssignment(assignmentBuildingDTO);
    }

}
