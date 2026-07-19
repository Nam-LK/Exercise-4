package com.javaweb.repository.custom.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CustomerRepository customerRepository;
    public static void joinTable(CustomerSearchBuilder builder, StringBuilder sql){
        Long staffId = builder.getStaffId();
        if (staffId != null) {
            sql.append(" INNER JOIN assignmentcustomer ON cusomer.id = assignmentcustomer.customerid ");
            sql.append(("INNER JOIN user ON assignmentcustomer.staffid = user.id"));
        }
    }

    public static void queryNormal(CustomerSearchBuilder builder, StringBuilder where){
        try {
            Field [] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true); //cho phép truy cập các biến đang ở private.
                //Nếu là public thì không cần
                String fieldName = item.getName();
                if (!fieldName.equals("staffId")) {
                    Object value = item.get(builder);
                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long")
                                || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND customer." + toLowerCase(fieldName) + " = " + value);
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND customer." + toLowerCase(fieldName) + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //chuyển string về dạng thường hết
    private static String toLowerCase(String fieldName) {
        return fieldName.toLowerCase();
    }

    public static void querySpecial(CustomerSearchBuilder builder, StringBuilder where){
        Long staffId = builder.getStaffId();
        if (staffId != null) {
            where.append(" AND assignmentcustomer.staffid = " + staffId);
        }
    }
    @Override
    public List<CustomerEntity> searchCustomer(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT * FROM customer ");
        joinTable(customerSearchBuilder, sql);
        StringBuilder where = new StringBuilder(SystemConstant.ONE_EQUAL_ONE);
        queryNormal(customerSearchBuilder, where);
        querySpecial(customerSearchBuilder, where);
        sql.append(where);
        Query query = em.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public void addOrUpdateCustomer(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity getCustomer(Long customerId) {
        return customerRepository.findById(customerId).get();
    }
}
