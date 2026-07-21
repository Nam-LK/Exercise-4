package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;

public class TransactionResponse extends AbstractDTO {
    private String detailTransaction;

    public String getDetailTransaction() {
        return detailTransaction;
    }

    public void setDetailTransaction(String detailTransaction) {
        this.detailTransaction = detailTransaction;
    }
}
