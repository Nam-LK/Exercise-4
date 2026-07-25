package com.javaweb.service.impl;

import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


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
