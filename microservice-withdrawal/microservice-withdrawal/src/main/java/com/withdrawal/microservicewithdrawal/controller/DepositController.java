package com.withdrawal.microservicewithdrawal.controller;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.withdrawal.microservicewithdrawal.domain.Transaction;
import com.withdrawal.microservicewithdrawal.domain.TransactionRedis;
import com.withdrawal.microservicewithdrawal.producer.DepositEventProducer;
import com.withdrawal.microservicewithdrawal.service.ITransactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {
    private Logger log = LoggerFactory.getLogger(DepositController.class); 

    @Autowired
    DepositEventProducer producer;

    @Autowired
    private ITransactionService service;

    @PostMapping("/v1/withdrawal")
    public ResponseEntity<Transaction> posrDeposit(@RequestBody Transaction transaction)  throws JsonProcessingException{
        log.info("antes de tranSql");
        Transaction sqlTransaction = service.save(transaction);
        log.info("despues de tranSql");
		log.info("antes  de sendDepositEvent");
        producer.sendDepositEvent(sqlTransaction);
        log.info("despues  de sendDepositEvent");
        return ResponseEntity.status(HttpStatus.CREATED).body(sqlTransaction);

    }


    @GetMapping("/all")
	public Map<String, TransactionRedis> all() {
		return service.findAll();
	}


    @GetMapping("/redis/transaction/{id}")
	public TransactionRedis findById(@PathVariable String id) {
		return service.findByRedis(id);
	}
    


    
}