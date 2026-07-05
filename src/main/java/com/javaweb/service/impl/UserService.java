package com.javaweb.service.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.converter.UserConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.MyException;
import com.javaweb.model.dto.AssignBuildingDTO;
import com.javaweb.model.dto.PasswordDTO;
import com.javaweb.model.dto.UserDTO;
import com.javaweb.model.response.ResponseAssignDTO;
import com.javaweb.model.response.StaffResponseDTO;
//import com.javaweb.repository.AssignBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private UserConverter userConverter;
    private BuildingRepository buildingRepository;
//    private AssignBuildingRepository assignBuildingRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                       UserConverter userConverter, BuildingRepository buildingRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
        this.buildingRepository = buildingRepository;
//        this.assignBuildingRepository = assignBuildingRepository;
    }

    @Override
    public UserDTO findOneByUserNameAndStatus(String name, int status) {
        return userConverter.convertToDto(userRepository.findOneByUserNameAndStatus(name, status));
    }

    @Override
    public List<UserDTO> getUsers(String searchValue, Pageable pageable) {
        Page<UserEntity> users = null;
        if (StringUtils.isNotBlank(searchValue)) {
            users = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0, pageable);
        } else {
            users = userRepository.findByStatusNot(0, pageable);
        }
        List<UserEntity> newsEntities = users.getContent();
        List<UserDTO> result = new ArrayList<>();
        for (UserEntity userEntity : newsEntities) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setRoleCode(userEntity.getRoles().get(0).getCode());
            result.add(userDTO);
        }
        return result;
    }



    @Override
    public List<UserDTO> getAllUsers(Pageable pageable) {
        List<UserEntity> userEntities = userRepository.getAllUsers(pageable);
        List<UserDTO> results = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = userConverter.convertToDto(userEntity);
            userDTO.setRoleCode(userEntity.getRoles().get(0).getCode());
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public int countTotalItems() {
        return userRepository.countTotalItem();
    }

    @Override
    public Map<Long, String> getListStaff() {
        Map<Long, String> map = new HashMap<>();
        List<UserEntity> list = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        for (UserEntity x : list) {
            map.put(x.getId(), x.getFullName());
        }
        return map;
    }

    @Override
    public StaffResponseDTO listStaff(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> userEntityList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
//        List<AssignBuildingEntity> assignBuildingEntities = buildingEntity.getAssignBuildingEntities();
//        List<UserEntity> staffAssignedBuilding = new ArrayList<>();
//        List<ResponseAssignDTO> list = new ArrayList<>();
//        assignBuildingEntities.stream().forEach(it -> staffAssignedBuilding.add(it.getUserEntity()));
//        for (UserEntity x : userEntityList) {
//            ResponseAssignDTO staffDTO = new ResponseAssignDTO();
//            if (staffAssignedBuilding.contains(x)) {
//                staffDTO.setChecked("checked");
//            } else {
//                staffDTO.setChecked("");
//            }
//            staffDTO.setStaffId(x.getId());
//            staffDTO.setFullName(x.getFullName());
//            list.add(staffDTO);
//        }
//        StaffResponseDTO result = new StaffResponseDTO();
//        result.setData(list);
//        result.setMessage("success");
//        result.setDetail("");
//        return result;
        List<UserEntity> staffAssignBuilding = buildingEntity.getUserEntities();
        List<ResponseAssignDTO> list = new ArrayList<>();
        for(UserEntity x : userEntityList){
            ResponseAssignDTO staffDTO = new ResponseAssignDTO();
            if(staffAssignBuilding.contains(x)){
                staffDTO.setChecked("checked");
            }else{
                staffDTO.setChecked("");
            }
            staffDTO.setStaffId(x.getId());
            staffDTO.setFullName(x.getFullName());
            list.add(staffDTO);
        }
        StaffResponseDTO result = new StaffResponseDTO();
        result.setData(list);
        result.setMessage("success");
        result.setDetail("");
        return result;
    }

    @Override
    public void updateAssignBuilding(AssignBuildingDTO assignBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignBuildingDTO.getBuildingId()).get();
        List<UserEntity> userEntities = userRepository.findByIdIn(assignBuildingDTO.getStaffs());
        buildingEntity.setUserEntities(userEntities);
//        List<UserEntity> staffAssignedBuilding = userRepository.findByIdIn(assignBuildingDTO.getStaffs());
//        List<AssignBuildingEntity> allAssignEntity = assignBuildingRepository.findAll();
//        allAssignEntity.stream()
//                .filter(it -> it.getBuildingEntity().equals(buildingEntity))
//                .forEach(it->assignBuildingRepository.deleteById(it.getId()));
//        for (UserEntity item : staffAssignedBuilding) {
//            AssignBuildingEntity assignBuilding = new AssignBuildingEntity();
//            assignBuilding.setBuildingEntity(buildingEntity);
//            assignBuilding.setUserEntity(item);
//            assignBuildingRepository.save(assignBuilding);
//        }
        buildingRepository.save(buildingEntity);
    }


    @Override
    public int getTotalItems(String searchValue) {
        int totalItem = 0;
        if (StringUtils.isNotBlank(searchValue)) {
            totalItem = (int) userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(searchValue, searchValue, 0);
        } else {
            totalItem = (int) userRepository.countByStatusNot(0);
        }
        return totalItem;
    }

    @Override
    public UserDTO findOneByUserName(String userName) {
        UserEntity userEntity = userRepository.findOneByUserName(userName);
        UserDTO userDTO = userConverter.convertToDto(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO findUserById(long id) {
        UserEntity entity = userRepository.findById(id).get();
        List<RoleEntity> roles = entity.getRoles();
        UserDTO dto = userConverter.convertToDto(entity);
        roles.forEach(item -> {
            dto.setRoleCode(item.getCode());
        });
        return dto;
    }

    @Override
    @Transactional
    public UserDTO insert(UserDTO newUser) {
        RoleEntity role = roleRepository.findOneByCode(newUser.getRoleCode());
        UserEntity userEntity = userConverter.convertToEntity(newUser);
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setStatus(1);
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO updateUser) {
        RoleEntity role = roleRepository.findOneByCode(updateUser.getRoleCode());
        UserEntity oldUser = userRepository.findById(id).get();
        UserEntity userEntity = userConverter.convertToEntity(updateUser);
        userEntity.setUserName(oldUser.getUserName());
        userEntity.setStatus(oldUser.getStatus());
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setPassword(oldUser.getPassword());
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void updatePassword(long id, PasswordDTO passwordDTO) throws MyException {
        UserEntity user = userRepository.findById(id).get();
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())
                && passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new MyException(SystemConstant.CHANGE_PASSWORD_FAIL);
        }
    }

    @Override
    @Transactional
    public UserDTO resetPassword(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO updateProfileOfUser(String username, UserDTO updateUser) {
        UserEntity oldUser = userRepository.findOneByUserName(username);
        oldUser.setFullName(updateUser.getFullName());
        return userConverter.convertToDto(userRepository.save(oldUser));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (Long item : ids) {
            UserEntity userEntity = userRepository.findById(item).get();
            userEntity.setStatus(0);
            userRepository.save(userEntity);
        }
    }
}
