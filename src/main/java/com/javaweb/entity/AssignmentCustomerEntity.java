package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "assignmentcustomer")
public class AssignmentCustomerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity staffs;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;



}
