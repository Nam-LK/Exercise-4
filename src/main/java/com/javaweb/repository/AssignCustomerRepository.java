package com.javaweb.repository;

import com.javaweb.entity.AssignCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignCustomerRepository extends JpaRepository<AssignCustomerEntity, Long> {
    List<AssignCustomerEntity> findByCustomerId(Long customerId);
}
