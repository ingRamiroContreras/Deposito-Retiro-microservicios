package com.deposit.depositservice.service;

import java.util.Map;

import com.deposit.depositservice.domain.Transaction;
import com.deposit.depositservice.domain.TransactionRedis;

public interface ITransactionService {

    public Transaction findById(Integer id); 

    public Transaction save(Transaction transaction);

    public TransactionRedis findByRedis(String id);

    public void save(TransactionRedis transactionRedis);
    public Map<String, TransactionRedis>findAll();
    
}