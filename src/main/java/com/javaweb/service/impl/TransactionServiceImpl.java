package com.javaweb.service.impl;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<TransactionResponse> transactionList(String code, Long customerId) {
        List<TransactionEntity> transactionEntityList = transactionRepository.findByCustomerId(customerId);
        List<TransactionResponse> transactionResponseList = new ArrayList<>();
        for (TransactionEntity transactionEntity : transactionEntityList) {
            if(transactionEntity.getType().getCode().equals(code)) {
                TransactionResponse transactionResponse = new TransactionResponse();
                String note = transactionEntity.getNote();
                transactionResponse.setDetailTransaction(note);
                transactionResponseList.add(transactionResponse);
            }
        }
        return transactionResponseList;
    }
}
