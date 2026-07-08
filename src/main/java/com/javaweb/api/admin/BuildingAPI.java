package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @GetMapping
    public List<BuildingSearchResponse> getBuilding(@ModelAttribute BuildingSearchRequest  buildingSearchRequest, Pageable pageable) {
        List<BuildingSearchResponse> res = buildingService.findAll(buildingSearchRequest, pageable);
        return res;

    }
    @PostMapping("/amdin/building")
    public void addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        return ResponseEntity.ok(buildingService.addOrUpdateBuilding(buildingDTO));
    }

    @DeleteMapping("/api/building/{ids}")
    public void deleteBuilding(@PathVariable Long[] ids){
        buildingService.deleteBuildings(ids);
    }
    
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadAllStaffs(@PathVariable Long id){ //hàm lấy ra danh sách các nhân viên quản lý tòa nhà có buildingid = a nào đó
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
        
    }

    @PostMapping
    public void updateAssignment(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        assignmentBuildingService.addAssignmentBuildingEntity(assignmentBuildingDTO);
    }

}
