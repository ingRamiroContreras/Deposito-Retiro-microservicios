package com.withdrawal.microservicewithdrawal.dao;

import java.util.Map;

import com.withdrawal.microservicewithdrawal.domain.TransactionRedis;

public interface ItransactionRedis {
    
    void save(TransactionRedis transaction);

	Map<String, TransactionRedis> findAll();

	TransactionRedis findById(String id);

	void update(TransactionRedis transaction);

	void delete(String id);


}