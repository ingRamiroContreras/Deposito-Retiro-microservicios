package com.deposit.depositservice.service;

import java.util.Map;

import com.deposit.depositservice.dao.ItransactionRedis;
import com.deposit.depositservice.dao.TransactionDao;
import com.deposit.depositservice.domain.Transaction;
import com.deposit.depositservice.domain.TransactionRedis;

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