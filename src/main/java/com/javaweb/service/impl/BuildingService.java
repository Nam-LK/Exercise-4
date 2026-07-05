package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.converter.BuildingSearchResConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseTypeDTO;
import com.javaweb.model.response.TypeCodeResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {
    private BuildingRepository buildingRepository;
    private BuildingConverter buildingConverter;
    private BuildingSearchResConverter buildingSearchResConverter;
    private RentAreaRepository rentAreaRepository;
    private BuildingSearchBuilderConverter builderConverter;
    private UploadFileUtils uploadFileUtils;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository, BuildingConverter buildingConverter,
                           BuildingSearchResConverter buildingSearchResConverter, RentAreaRepository rentAreaRepository,
                           BuildingSearchBuilderConverter builderConverter,UploadFileUtils uploadFileUtils) {
        this.buildingRepository = buildingRepository;
        this.buildingConverter = buildingConverter;
        this.buildingSearchResConverter = buildingSearchResConverter;
        this.rentAreaRepository = rentAreaRepository;
        this.builderConverter = builderConverter;
        this.uploadFileUtils = uploadFileUtils;
    }

    @Override
    public List<BuildingSearchResponse> searchBuilding(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = builderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> list = buildingRepository.searchBuildings(buildingSearchBuilder);
        List<BuildingSearchResponse> responseList = new ArrayList<>();
        for (BuildingEntity item : list) {
            BuildingSearchResponse buildingSearchResponse = buildingSearchResConverter.toBuildingSearchResponse(item);
            responseList.add(buildingSearchResponse);
        }
        return responseList;
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        Optional<BuildingEntity> opt = buildingRepository.findById(id);
        BuildingEntity buildingEntity = new BuildingEntity();
        if (opt.isPresent()) {
            buildingEntity = opt.get();
        }
        return buildingConverter.convertToDTO(buildingEntity);
    }

    @Override
    public void createOrUpdateBuilding(BuildingDTO buildingDTO) {
//        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
//        List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findAllByBuildingId(buildingEntity.getId());
//        List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findAll();
//        Long buildingId = buildingDTO.getId();
//        for (RentAreaEntity it : rentAreaEntities) {
//            if (rentAreaEntityList.contains(it)) {
//                rentAreaRepository.deleteById(it.getId());
//            }
//        }
//        List<RentAreaEntity> rentAreaUpdate = buildingEntity.getRentAreaEntities();
//        if (!buildingDTO.getRentArea().isEmpty()) {
//            rentAreaRepository.saveAll(rentAreaUpdate);
//        }
//        if(buildingId!=null){
//            BuildingEntity updatedBuilding = buildingRepository.findById(buildingId).orElseThrow(()->new NotFoundException("Building Not Found"));
//            buildingEntity.setImage(updatedBuilding.getImage());
//        }
//        saveThumnail(buildingDTO,buildingEntity);
//        return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
        Long buildingId = buildingDTO.getId();
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        if(buildingId!=null){
            BuildingEntity updatedBuilding = buildingRepository.findById(buildingId).orElseThrow(()->new NotFoundException("Building Not Found"));
            buildingEntity.setImage(updatedBuilding.getImage());
        }
        saveThumnail(buildingDTO,buildingEntity);
        buildingRepository.save(buildingEntity);
    }

    @Transactional
    @Override
    public void deleteBuilding(List<Long> ids) {
//        for (Long item : ids) {
//            rentAreaRepository.deleteByBuildingId(item);
//            buildingRepository.deleteById(item);
//        }
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public Page<BuildingSearchResponse> getBuildings(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        BuildingSearchBuilder buildingSearchBuilder = builderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        Page<BuildingEntity> buildingEntityPage = buildingRepository.getPageBuildings(buildingSearchBuilder,pageable);
        return buildingEntityPage.map(buildingSearchResConverter::toBuildingSearchResponse);
    }

    @Override
    public int countTotalItem() {
        return buildingRepository.countTotalItem();
    }

    @Override
    public TypeCodeResponseDTO listTypeCode(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<String> currentTypeCode = Arrays.stream(buildingEntity.getTypeCode().split(",")).collect(Collectors.toList());
        Map<String,String> typeCodeList = TypeCode.getRentType();
        List<ResponseTypeDTO> list = new ArrayList<>();
        for(Map.Entry<String,String> it : typeCodeList.entrySet()){
            ResponseTypeDTO responseTypeDTO = new ResponseTypeDTO();
            if(currentTypeCode.contains(it.getKey())){
                responseTypeDTO.setChecked("checked");
            }else{
                responseTypeDTO.setChecked("");
            }
            responseTypeDTO.setTypeCode(it.getKey());
            responseTypeDTO.setTypeName(it.getValue());
            list.add(responseTypeDTO);
        }
        TypeCodeResponseDTO typeCodeResponseDTO = new TypeCodeResponseDTO();
        typeCodeResponseDTO.setData(list);
        return typeCodeResponseDTO;
    }

    private void saveThumnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (buildingDTO.getImageBase64() != null) {
            if (buildingEntity.getImage() != null) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path,bytes);
            buildingEntity.setImage(path);
        }
    }
}
