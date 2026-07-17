package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "buildingrenttype")
public class BuildingRentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buildingid")
    private Long buildingid;

    @Column(name = "renttypeid")
    private Long renttypeid;


}
