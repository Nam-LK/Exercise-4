package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ITransactionService {
    List<TransactionEntity> findByCodeAndCustomerId(String code, Long customerId);
    void addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO);
    public TransactionResponseDTO loadTransactionDetail(Long id);
}
