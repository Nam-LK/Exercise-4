package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum TypeCode {
    NOI_THAT("Nội thất"),
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn");

    private final String typeCode;
    TypeCode(String typeCode){
        this.typeCode = typeCode;
    }
    public String getTypeName(){
        return typeCode;
    }
    public static Map<String,String> getRentType(){
        Map<String,String> map = new LinkedHashMap<>();
        for(TypeCode item : TypeCode.values()){
            map.put(item.toString(),item.getTypeName());
        }
        return map;
    }
}
