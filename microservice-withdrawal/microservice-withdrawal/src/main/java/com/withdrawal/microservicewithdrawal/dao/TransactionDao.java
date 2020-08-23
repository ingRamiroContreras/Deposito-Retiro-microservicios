package com.withdrawal.microservicewithdrawal.dao;

import com.withdrawal.microservicewithdrawal.domain.Transaction;

import org.springframework.data.repository.CrudRepository;

public interface TransactionDao extends CrudRepository<Transaction,Integer>{


    
}