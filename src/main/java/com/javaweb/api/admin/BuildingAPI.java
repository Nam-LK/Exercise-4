package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.model.response.TypeCodeResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
    private IUserService iUserService;
    private IBuildingService iBuildingService;

    @Autowired
    public BuildingAPI(IUserService iUserService, IBuildingService iBuildingService) {
        this.iUserService = iUserService;
        this.iBuildingService = iBuildingService;
    }

    @PostMapping
    public void createBuilding(@RequestBody BuildingDTO buildingDTO) {
        iBuildingService.createOrUpdateBuilding(buildingDTO);
    }

    @GetMapping("/{buildingId}/staffs")
    public StaffResponseDTO loadStaff(@PathVariable Long buildingId) {
        StaffResponseDTO staffResponseDTO = iUserService.listStaff(buildingId);
        return staffResponseDTO;
    }

    @PutMapping("/{buildingId}")
    public void updateBuilding(@RequestBody BuildingDTO buildingDTO, @PathVariable Long buildingId) {
        iBuildingService.createOrUpdateBuilding(buildingDTO);
    }

    @DeleteMapping
    public void deleteBuilding(@RequestBody List<Long> ids) {
        if (ids.size() > 0) {
            iBuildingService.deleteBuilding(ids);
        }
    }

    @GetMapping("/{buildingId}/types")
    public TypeCodeResponseDTO loadTypeCode(@PathVariable Long buildingId) {
        TypeCodeResponseDTO typeCodeResponseDTO = iBuildingService.listTypeCode(buildingId);
        return typeCodeResponseDTO;
    }

}