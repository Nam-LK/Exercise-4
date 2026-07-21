package com.javaweb.service.impl;

import com.javaweb.converter.TransactionResponseConverter;
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
    @Autowired
    private TransactionResponseConverter transactionResponseConverter;

//    @Override
//    public List<TransactionResponse> transactionList(String code, Long id) {
//        List<TransactionEntity> list = transactionRepository.findByCustomerIdAndCode(id,code);
//        List<TransactionResponse> responseList = new ArrayList<>();
//        for (TransactionEntity transactionEntity : list) {
//            TransactionResponse transactionResponse = transactionResponseConverter.toTransactionResponse(transactionEntity);
//            responseList.add(transactionResponse);
//        }
//        return responseList;
//    }
}
