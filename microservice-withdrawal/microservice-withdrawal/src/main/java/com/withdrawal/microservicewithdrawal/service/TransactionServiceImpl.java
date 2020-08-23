package com.withdrawal.microservicewithdrawal.service;

import java.util.Map;

import com.withdrawal.microservicewithdrawal.domain.Transaction;
import com.withdrawal.microservicewithdrawal.domain.TransactionRedis;
import com.withdrawal.microservicewithdrawal.dao.ItransactionRedis;
import com.withdrawal.microservicewithdrawal.dao.TransactionDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private ItransactionRedis transactionRedis;

    @Override
    public Transaction findById(Integer id) {
        return transactionDao.findById(id).orElse(null);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    @Override
    public TransactionRedis findByRedis(String id) {
        
        return transactionRedis.findById(id);
    }

    @Override
    public void save(TransactionRedis transactionRed) {
        transactionRedis.save(transactionRed);
    }

    @Override
    public Map<String, TransactionRedis> findAll() {
        
        return transactionRedis.findAll();
    }

    
}