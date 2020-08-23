package com.deposit.depositservice.dao;

import java.util.Map;

import com.deposit.depositservice.domain.TransactionRedis;

public interface ItransactionRedis {
    
    void save(TransactionRedis transaction);

	Map<String, TransactionRedis> findAll();

	TransactionRedis findById(String id);

	void update(TransactionRedis transaction);

	void delete(String id);


}