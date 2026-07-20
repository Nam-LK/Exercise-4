package com.javaweb.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "type")
    private TransactionTypeEntity type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public TransactionTypeEntity getType() {
        return type;
    }

    public void setType(TransactionTypeEntity type) {
        this.type = type;
    }
}
