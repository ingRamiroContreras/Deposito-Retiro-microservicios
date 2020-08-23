package com.historical.historicalmicroservice.service;

import com.historical.historicalmicroservice.entity.Transaction;

public interface ItransactionService {
    
    public Transaction save(Transaction transaction);
    public Iterable<Transaction> findByAccountId(Integer accountId);
    public Iterable<Transaction> findAll();

}