package com.withdrawal.microservicewithdrawal.service;

import java.util.Map;

import com.withdrawal.microservicewithdrawal.domain.Transaction;
import com.withdrawal.microservicewithdrawal.domain.TransactionRedis;


public interface ITransactionService {

    public Transaction findById(Integer id); 

    public Transaction save(Transaction transaction);

    public TransactionRedis findByRedis(String id);

    public void save(TransactionRedis transactionRedis);
    public Map<String, TransactionRedis>findAll();
    
}