package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.TransactionTypeEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.TransactionTypeRepository;
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
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionTypeRepository transactionTypeRepository;


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

    @Override
    public void createOrUpdateTransaction(Long customerId, TransactionDTO transactionDTO) {
        Long transactionId = transactionDTO.getId();
        String code = transactionDTO.getCode();
        List<TransactionEntity> transactionEntityList = transactionRepository.findByCustomerId(customerId);
        if (transactionId != null) {//sửa cái đã có
            for (TransactionEntity transactionEntity : transactionEntityList) {
                if(code.equals(transactionEntity.getType().getCode())) {
                    transactionEntity.setNote(transactionDTO.getDetails());
                    transactionRepository.save(transactionEntity);
                }
            }
        }else{ //thêm mới
            CustomerEntity customerEntity = customerRepository.findById(customerId).get();
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setNote(transactionDTO.getDetails());
            transactionEntity.setCustomer(customerEntity);
            TransactionTypeEntity transactionTypeEntity = transactionTypeRepository.findByCode(code);
            transactionEntity.setType(transactionTypeEntity);
            transactionEntityList.add(transactionEntity);
            customerEntity.setTransactions(transactionEntityList);
            customerRepository.save(customerEntity);
        }

    }
}
