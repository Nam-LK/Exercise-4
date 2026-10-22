
package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.model.response.TypeCodeResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    private final IUserService iUserService;
    private final IBuildingService iBuildingService;

    public BuildingAPI(
            IUserService iUserService,
            IBuildingService iBuildingService) {
        this.iUserService = iUserService;
        this.iBuildingService = iBuildingService;
    }

    @PostMapping
    public void createBuilding(@RequestBody BuildingDTO buildingDTO) {
        iBuildingService.createOrUpdateBuilding(buildingDTO);
    }

    @PutMapping("/{buildingId}")
    public void updateBuilding(
            @RequestBody BuildingDTO buildingDTO,
            @PathVariable Long buildingId) {

        buildingDTO.setId(buildingId);
        iBuildingService.createOrUpdateBuilding(buildingDTO);
    }

    @GetMapping("/{buildingId}/staffs")
    public StaffResponseDTO loadStaff(
            @PathVariable Long buildingId) {
        return iUserService.listStaff(buildingId);
    }

    @DeleteMapping
    public void deleteBuilding(@RequestBody List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            iBuildingService.deleteBuilding(ids);
        }
    }

    @GetMapping("/{buildingId}/types")
    public TypeCodeResponseDTO loadTypeCode(
            @PathVariable Long buildingId) {
        return iBuildingService.listTypeCode(buildingId);
    }
}
