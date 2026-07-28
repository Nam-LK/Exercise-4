package com.javaweb.repository;

import com.javaweb.entity.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeEntity, Long> {
    TransactionTypeEntity findByCode(String code);
}
