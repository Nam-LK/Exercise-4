package com.javaweb.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Long value;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildingId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) { this.id = id;}

    public Long getValue() {
        return value;
    }
    public void setValue(Long value) {
        this.value = value;
    }
    public BuildingEntity getBuildingId(){
        return buildingId;
    }
    public void setBuildingId(BuildingEntity buildingId){
        this.buildingId = buildingId;
    }
}
