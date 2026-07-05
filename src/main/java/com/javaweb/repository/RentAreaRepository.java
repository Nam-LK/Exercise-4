package com.javaweb.repository;

import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity,Long> {
    @Query(value = "Select * from rentarea where buildingid =?1",nativeQuery = true)
    List<RentAreaEntity> findAllByBuildingId(Long id);
    @Modifying
    @Transactional
    @Query(value = "delete from rentarea  where buildingid =?1",nativeQuery = true)
    void deleteByBuildingId(Long id);
}
