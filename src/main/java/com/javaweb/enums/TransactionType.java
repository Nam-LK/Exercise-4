package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");

    private String description;
    private TransactionType(String description) {
        this.description = description;
    }
    public static Map<String, String> transactionType() {
        Map<String, String> mp = new HashMap<>();
        for (TransactionType t : TransactionType.values()) {
            mp.put(t.name(), t.description);
        }
        return mp;
    }
}
