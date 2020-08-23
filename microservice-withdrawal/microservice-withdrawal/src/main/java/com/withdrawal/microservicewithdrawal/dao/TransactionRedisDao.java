package com.withdrawal.microservicewithdrawal.dao;

import java.util.Map;

import javax.annotation.PostConstruct;

import com.withdrawal.microservicewithdrawal.domain.TransactionRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TransactionRedisDao implements ItransactionRedis {

    @Autowired
    private RedisTemplate<String, TransactionRedis> template;

    private HashOperations hashOperation;

    String key="TRANSACTION";

    @PostConstruct
    private void init(){
        hashOperation=template.opsForHash();
    }

    @Override
    public void save(final TransactionRedis transaction) {
        hashOperation.put(key, transaction.getId(), transaction);
    }

    @Override
    public Map<String, TransactionRedis> findAll() {

        return hashOperation.entries(key);
    }

    @Override
    public TransactionRedis findById(final String id) {
        return (TransactionRedis) hashOperation.get(key, id);
    }

    @Override
    public void update(final TransactionRedis transaction) {
        save(transaction);
    }

    @Override
    public void delete(final String id) {
        hashOperation.delete(key,id);
    }
    
}