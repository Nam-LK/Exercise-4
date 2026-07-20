package com.javaweb.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactiontype")
public class TransactionTypeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<TransactionEntity>  transactionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TransactionEntity> getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(List<TransactionEntity> transactionType) {
        this.transactionType = transactionType;
    }
}
