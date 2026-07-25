package com.javaweb.entity;


import javax.persistence.*;

@Entity
@Table(name = "buildingrenttype")
public class BuildingRentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildingEntity;

    @ManyToOne
    @JoinColumn(name = "renttypeid")
    private RentTypeEntity rentTypeEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuildingEntity getBuildingEntity() {
        return buildingEntity;
    }

    public void setBuildingEntity(BuildingEntity buildingEntity) {
        this.buildingEntity = buildingEntity;
    }

    public RentTypeEntity getRentTypeEntity() {
        return rentTypeEntity;
    }

    public void setRentTypeEntity(RentTypeEntity rentTypeEntity) {
        this.rentTypeEntity = rentTypeEntity;
    }
}
