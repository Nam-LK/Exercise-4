package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @PostMapping("/amdin/building")
    public void addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        //bên thêm mới tòa nhà có bao nhiêu field thì bên dto có đúng như vậy
        //xuống service xuống db để update hoặc thêm mới
    }

    @DeleteMapping("/api/building/{id}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        //xuống DB xóa theo danh sách id gửi về

    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadAllStaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
        
    }

}
