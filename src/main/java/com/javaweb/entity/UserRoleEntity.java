//package com.javaweb.entity;
//
//
//import javax.persistence.*;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Table;
//import java.util.Date;
//
//@Entity
//@Table(name = "user_role")
//public class UserRoleEntity{
//
////    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "userid")
//    private UserEntity users;
//
//	@ManyToOne
//    @JoinColumn(name = "roleid")
//    private RoleEntity roles;
//
//    @Column(name = "createddate")
//    private Date createddate;
//
//    @Column(name = "modifieddate")
//    private Date modifieddate;
//
//    @Column(name = "createdby")
//    private String createdby;
//
//    @Column(name = "modifiedby")
//    private String modifiedby;
//
//    public UserEntity getUsers() {
//        return users;
//    }
//
//    public void setUsers(UserEntity users) {
//        this.users = users;
//    }
//
//    public RoleEntity getRoles() {
//        return roles;
//    }
//
//    public void setRoles(RoleEntity roles) {
//        this.roles = roles;
//    }
//
//    public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//    public Date getCreateddate() {
//        return createddate;
//    }
//
//    public void setCreateddate(Date createddate) {
//        this.createddate = createddate;
//    }
//
//    public Date getModifieddate() {
//        return modifieddate;
//    }
//
//    public void setModifieddate(Date modifieddate) {
//        this.modifieddate = modifieddate;
//    }
//
//    public String getCreatedby() {
//        return createdby;
//    }
//
//    public void setCreatedby(String createdby) {
//        this.createdby = createdby;
//    }
//
//    public String getModifiedby() {
//        return modifiedby;
//    }
//
//    public void setModifiedby(String modifiedby) {
//        this.modifiedby = modifiedby;
//    }
//}
