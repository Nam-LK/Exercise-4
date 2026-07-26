package com.javaweb.service;

import com.javaweb.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> transactionList(String code, Long customerId);
}