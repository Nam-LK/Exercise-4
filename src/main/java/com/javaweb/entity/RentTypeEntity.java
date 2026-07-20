package com.javaweb.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "renttype")
public class RentTypeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rentTypeEntity")
    List<BuildingRentTypeEntity> buildingRentTypeEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BuildingRentTypeEntity> getBuildingRentTypeEntityList() {
        return buildingRentTypeEntityList;
    }

    public void setBuildingRentTypeEntityList(List<BuildingRentTypeEntity> buildingRentTypeEntityList) {
        this.buildingRentTypeEntityList = buildingRentTypeEntityList;
    }
}
