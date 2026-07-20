package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = -4988455421375043688L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "status", nullable = false)
    private Integer status;

    @OneToMany(mappedBy="userEntity", fetch = FetchType.LAZY)
    private List<AssignBuildingEntity> assignBuildingEntities = new ArrayList<>();

//    @OneToMany(mappedBy="users", fetch = FetchType.LAZY)
//    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    private List<AssignCustomerEntity> assignCustomerEntities = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<AssignBuildingEntity> getAssignBuildingEntities() {
        return assignBuildingEntities;
    }

    public void setAssignBuildingEntities(List<AssignBuildingEntity> assignBuildingEntities) {
        this.assignBuildingEntities = assignBuildingEntities;
    }

//    public List<UserRoleEntity> getUserRoleEntities() {
//        return userRoleEntities;
//    }
//
//    public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
//        this.userRoleEntities = userRoleEntities;
//    }


    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<AssignCustomerEntity> getAssignCustomerEntities() {
        return assignCustomerEntities;
    }

    public void setAssignCustomerEntities(List<AssignCustomerEntity> assignCustomerEntities) {
        this.assignCustomerEntities = assignCustomerEntities;
    }
}
