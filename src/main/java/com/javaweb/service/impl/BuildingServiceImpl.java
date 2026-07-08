package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.StringUtils;
import com.javaweb.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        List<String> typeCodes = buildingSearchRequest.getTypeCode();
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest, typeCodes);

        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder, pageable);
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();

        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingconverter.toBuildingSearchResponse(buildingEntity);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }

    @Override
    public void deleteBuildings(Long[] ids) {
        rentAreaService.deleteByBuildings(ids);
        assignmentBuildingService.deleteByBuildingsIn(ids);
        for (Long id : ids) {
            buildingRepository.deleteById(id);
        }

    }

    @Override
    public BuildingDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
        if(!checkAddBuilding(buildingDTO)) return null;
        Long builaingId = buildingDTO.getId();
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setTypeCode(removeAccent(buildingDTO.getTypeCode()));
        if (buildingId != null) { // update
            BuildingEntity foundBuilding = buildingRepository.findById(buildingId)
                    .orElseThrow(() -> new NotFoundException("Building not found!"));
            buildingEntity.setImage(foundBuilding.getImage());
        }
            saveThumbnail(buildingDTO, buildingEntity);
            buildingRepository.save(buildingEntity);//có id nó tự xem là update
            buildingDTO.setId(buildingEntity.getId());
            if(StringUtils.check(buildingDTO.getRentArea())) rentAreaService.addRentArea(buildingDTO);
            return buildingDTO;
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
//        String path = "/building/" + buildingDTO.getImageName();
//        if (buildingDTO.getImageBase64() != null) {
//            if (buildingEntity.getImage() != null) {
//                if(!path.equals(buildingEntity.getImage())){
//                    File file = new File("C://home/office" + buildingEntity.getImage());
//                    file.delete();
//                }
//            }
//            byte[]bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
//            uploadFileUtils.writeOrUpdate(path, bytes);
//            buildingEntity.setImage(path);

        }
    }

    public static String removeAccent(List<String> typeCodes) {
        String s = String.join("", typeCodes);
        return s;
    }

    public static boolean checkAddBuilding(BuildingDTO buildingDTO) {
        if(!StringUtils.check(buildingDTO.getName())) return false;
        if(!StringUtils.check(buildingDTO.getDistrict())) return false;
        if(!StringUtils.check(buildingDTO.getWard())) return false;
        if(!StringUtils.check(buildingDTO.getStreet())) return false;
        if(!StringUtils.check(buildingDTO.getRentArea())) return false;
        if(!StringUtils.check(buildingDTO.getRentPriceDescription())) return false;

        if(!NumberUtils.checkNumber(buildingDTO.getNumberOfBasement())) return false;
        if(!NumberUtils.checkNumber(buildingDTO.getFloorArea())) return false;
        if(!NumberUtils.checkNumber(buildingDTO.getRentPrice())) return false;

        return true;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO res = modelMapper.map(buildingEntity, BuildingDTO.class);

        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreaEntities();
        String rentArea = rentAreaEntities.stream().map(it->it.getValue().toString()).collect(Collectors.joining(",");
        res.setImage(buildingEntity.getImage());

        res.setRentArea(rentArea);
        res.setTypeCode(toTypeCodeList(buildingEntity.getTypeCode()));

        return res;
    }

    public List<String> toTypeCodeList(String typeCodes) {
        String [] arr = typeCodes.split(",");
        List<String> typeCodeList = new ArrayList<>();
        for (String s : arr) {
            typeCodeList.add(s);
        }
        return typeCodeList;
    }

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssigment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity staff : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(staff.getFullName());
            staffResponseDTO.setStaffId(staff.getId());
            if (staffAssigment.contains(staff)) {
                staffResponseDTO.setChecked("checked");
            }else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOList.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOList);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public int countTotalItem(List<BuildingSearchResponse> list) {
        return 0;
    }
}
