package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public void multitableJoin(BuildingSearchBuilder builder, StringBuilder sql){
        Long staffId = builder.getStaffId();
        if(staffId!=null){
            sql.append(" inner join assignmentbuilding as ab on b.id = ab.buildingid");
        }
    }
    public void simpleQuery(BuildingSearchBuilder builder,StringBuilder where){
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId")&&!fieldName.startsWith("rentArea")
                        &&!fieldName.startsWith("rentPrice")&&!fieldName.equals("typeCode")){
                    Object value = item.get(builder);
                    if(value!=null){
                        if(item.getType().getName().equals("java.lang.String")&&!value.toString().equals("")){
                            where.append(" and b."+fieldName.toLowerCase()+" like'%"+value+"%'");
                        }
                        if(item.getType().getName().equals("java.lang.Integer")){
                            where.append(" and b."+fieldName.toLowerCase()+" = "+value);
                        }
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void specialQuery(BuildingSearchBuilder builder,StringBuilder where){
        Integer rentAreaFrom = builder.getRentAreaFrom();
        Integer rentAreaTo = builder.getRentAreaTo();
        Integer rentPriceFrom = builder.getRentPriceFrom();
        Integer rentPriceTo = builder.getRentPriceTo();
        Long staffId = builder.getStaffId();
        List<String> typeCode = builder.getTypeCode();
        if(rentPriceFrom!=null){
            where.append(" and b.rentprice >= "+rentPriceFrom);
        }
        if(rentPriceTo!=null){
            where.append(" and b.rentprice <= "+rentPriceTo);
        }
        if((rentAreaFrom!=null)||(rentAreaTo!=null)){
            where.append(" and EXISTS (Select * from rentarea as r where b.id = r.buildingid ");
            if(rentAreaFrom!=null){
                where.append(" and r.value >= "+rentAreaFrom);
            }
            if(rentAreaTo!=null){
                where.append(" and r.value <= "+rentAreaTo);
            }
            where.append(") ");
        }
        if(staffId!=null){
            where.append(" and ab.staffid = "+staffId);
        }
        if(typeCode!=null&&typeCode.size()>0){
            where.append(" and b.type in(");
            String query = typeCode.stream().map(it->"'"+it+"'").collect(Collectors.joining(","));
            where.append(query);
            where.append(") ");
        }
    }
    @Override
    public List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("Select b.* from building as b");
        multitableJoin(buildingSearchBuilder,sql);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        simpleQuery(buildingSearchBuilder,sql);
        specialQuery(buildingSearchBuilder,sql);
        sql.append(" group by b.id");
        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
        List<BuildingEntity> list = new ArrayList<>();
        list = (List<BuildingEntity>) query.getResultList();
        return list;
    }

    @Override
    public int countTotalItem() {
        StringBuilder sql = new StringBuilder("Select b.* from building as b");
        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
        List<BuildingEntity> list = query.getResultList();
        return list.size();
    }

    @Override
    public Page<BuildingEntity> getPageBuildings(BuildingSearchBuilder builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("Select b.* from building as b");
        multitableJoin(builder,sql);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        simpleQuery(builder,sql);
        specialQuery(builder,sql);
        sql.append(" group by b.id");
        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
        List<BuildingEntity> list = query.setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(list,pageable,countTotalItem());
    }
}
