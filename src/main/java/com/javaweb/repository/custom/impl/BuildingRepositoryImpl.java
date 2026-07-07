package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.NumberUtils;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        StringBuilder sql = new StringBuilder(
                "select *  from building b"
        );

        StringBuilder where = new StringBuilder("WHERE 1=1");

        joinExcute(buildingSearchBuilder, sql);
        queryNormal(buildingSearchBuilder,where);
        querySpecial(buildingSearchBuilder, where);

        groupByQuery(buildingSearchBuilder, where);

        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(BuildingSearchResponse buildingSearchResponse) {
        String sql = buildQueryFilter(buildingSearchResponse.getId());
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    private String buildQueryFilter(Long id) {
        String sql = "SELECT * FROM building WHERE id = "+ id;
        return sql;
    }

    public void splitPage(Pageable pageable, StringBuilder where){
        where.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
        .append(" OFFSET ").append(pageable.getOffset());
    }

     public void joinExcute(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql){
        Long staffId = buildingSearchBuilder.getStaffId();
        if (NumberUtils.checkNumber(staffId)) sql.append(" JOIN assignmentbuilding ON assignmentbuilding.buildingid = b.id ");
     }

     public void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();

            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();

                if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") &&
                    !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")){
                    Object value = item.get(buildingSearchBuilder);
                    if(value != null){
                        if(item.getType().getName().equals("java.lang. Long") || item.getType(). getName(). equals("java. lang. Integer")) {
                            where.append(" and b." + fieldName + " = " + value + " ");
                        }else if(item.getType().getName().equals("java.lang.String")){
                            where.append(" and b." + fieldName + " like '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     }

     public void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
         Long staffId = buildingSearchBuilder.getStaffId();
         if(NumberUtils.checkNumber(staffId)) where.append(" and assignmentBuilding.staffId = " + staffId + " ");

         Long rentAreaTo = buildingSearchBuilder.getAreaTo();
         Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();

         if(NumberUtils.checkNumber(rentAreaFrom) || NumberUtils.checkNumber(rentAreaTo)) {

             where.append(" and exists (select * from rentArea r where b.id =r.buildingId ");
             if (rentAreaFrom != null) where.append(" and r.value >= " + rentAreaFrom + " ");
             if (rentAreaTo != null) where.append(" and r.value <= " + rentAreaTo + " ");
             where.append(") ");
         }

         Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
         Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();

         if(NumberUtils.checkNumber(rentPiceFrom) || NumberUtils.checkNumber(rentPriceTo)) {
             if (rentPriceFrom != null) where.append(" and b.rentPrice >= " + rentPriceFrom + " ");
             if (rentPriceTo != null) where.append(" and b.rentPrice <= " + rentPriceTo + " ");
         }
         List<String> typeCode = buildingSearchBuilder.getTypeCode();
         if(typeCode != null && typeCode.size() != 0){
             where.append(" and ( ");
             String sql = typeCode.stream().map(it -> " b.type like " + "'%" + it + "%' ").collect(Collectors. joining( delimiter: " or ")
             where.append(sql + " ) ");
         }
     }

     public void groupByQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        where.append(" GROUP BY b.id ");
        if (buildingSearchBuilder.getStaffId() != null) {
            where.append(" , assignmentBuilding.staffId; ");
        }
     }

}
