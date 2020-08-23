package com.deposit.depositservice.dao;

import com.deposit.depositservice.domain.Transaction;

import org.springframework.data.repository.CrudRepository;

public interface TransactionDao extends CrudRepository<Transaction,Integer>{


    
}