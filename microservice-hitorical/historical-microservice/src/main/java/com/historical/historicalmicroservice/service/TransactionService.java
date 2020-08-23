package com.historical.historicalmicroservice.service;

import com.historical.historicalmicroservice.entity.Transaction;
import com.historical.historicalmicroservice.repository.ITransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ItransactionService {

    @Autowired
    ITransactionRepository repository;


    @Override
    public Transaction save(Transaction transaction) {
        
        return repository.save(transaction);
    }

    @Override
    public Iterable<Transaction> findByAccountId(Integer accountId) {
        
        return repository.findByAccountId(accountId);
    }

    @Override
    public Iterable<Transaction> findAll() {
        
        return repository.findAll();
    }
    
}