package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;

import java.util.List;
import java.util.stream.Collectors;

public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private BuildingRepository buildingRepository;

    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
            sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
            sql.append(" INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
        }
    }

    public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields(); //[name, floorArea, numberOfBasement,...]
            for (Field item : fields) {
                item.setAccessible(true); //cho phép truy cập các biến đang ở private.
                //Nếu là public thì không cần
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
                        && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSearchBuilder);
                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long")
                                || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b." + toLowerCase(fieldName) + " = " + value);
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b." + toLowerCase(fieldName) + " LIKE '%" + value + "%' ");
                        }
                    }
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
    //chuyển string về dạng thường hết
    private static String toLowerCase(String fieldName) {
        return fieldName.toLowerCase();
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) { //sử dụng để lấy ở các bảng join với bảng building
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" AND assignmentbuilding.staffid = " + staffId);
        }

        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        if (rentAreaTo != null || rentAreaFrom != null) {
            where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid ");
            if (rentAreaTo != null) {
                where.append(" AND r.value <= " + rentAreaTo);
            }
            if (rentAreaFrom != null) {
                where.append(" AND r.value >= " + rentAreaFrom);
            }
            where.append(" ) ");
        }

        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        if (rentPriceTo != null || rentPriceFrom != null) {
            if (rentPriceTo != null) {
                where.append(" AND b.rentprice <= " + rentPriceTo);
            }
            if (rentPriceFrom != null) {
                where.append(" AND b.rentprice >= " + rentPriceFrom);
            }
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
            where.append(" AND (");
            String sql = typeCode.stream().map(it -> "renttype.code like '%" + it + "%' ")
                    .collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(" ) ");
        }
    }

    @Override
    public List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("select b.* from building b");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(SystemConstant.ONE_EQUAL_ONE);
        queryNomal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        sql.append(where);
        sql.append("GROUP BY b.id ");
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public void addOrUpdateBuilding(BuildingEntity buildingEntity) {
        buildingRepository.save(buildingEntity);
    }

    @Override
    public void updateAssignBuilding(AssignBuildingEntity assignBuildingEntity) {

    }
}
