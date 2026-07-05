package com.javaweb.service.impl;

import com.javaweb.converter.TransactionConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDetailDTO;
import com.javaweb.model.response.TransactionResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    private TransactionRepository transactionRepository;
    private TransactionConverter transactionConverter;
    private CustomerRepository customerRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionConverter transactionConverter,CustomerRepository customerRepository){
        this.transactionRepository = transactionRepository;
        this.transactionConverter = transactionConverter;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<TransactionEntity> findByCodeAndCustomerId(String code, Long customerId) {
        return transactionRepository.findByCodeAndCustomerEntity_Id(code,customerId);
    }

    @Override
    public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = transactionConverter.toTransactionEntity(transactionDTO);
        CustomerEntity customerEntity = customerRepository.findById(transactionDTO.getCustomerId()).get();
        transactionEntity.setCustomerEntity(customerEntity);
        if(transactionDTO.getId()!=null){
            TransactionEntity oldTransactionEntity = transactionRepository.findById(transactionDTO.getId()).get();
//            transactionEntity.setCreatedDate(oldTransactionEntity.getCreatedDate());
//            transactionEntity.setCreatedBy(oldTransactionEntity.getCreatedBy());
//            transactionEntity.setCode(transactionDTO.getCode());
            transactionEntity.setCreatedDate(oldTransactionEntity.getCreatedDate());
            transactionEntity.setCreatedBy(oldTransactionEntity.getCreatedBy());
        }
        transactionRepository.save(transactionEntity);
    }

    @Override
    public TransactionResponseDTO loadTransactionDetail(Long id) {
        TransactionEntity transactionEntity = transactionRepository.findById(id).get();
        ResponseDetailDTO responseDetailDTO = new ResponseDetailDTO();
        responseDetailDTO.setNote(transactionEntity.getNote());
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setData(responseDetailDTO);
        return transactionResponseDTO;
    }
}
