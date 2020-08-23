package com.account.account.service;


import java.io.IOException;

import com.account.account.entity.Account;
import com.account.account.entity.Transaction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionEvents {

    @Autowired
    IAccountService accountService;

    @Autowired
    ObjectMapper objectMapper;

    public void processTransactionEvent(ConsumerRecord<Integer, String> consumerRecord)
            throws JsonParseException, JsonMappingException, IOException {
        double newAmount = 0;
        Account account = new Account();
        
        Transaction event = objectMapper.readValue(consumerRecord.value(), Transaction.class);

        account = accountService.findById(event.getAccountId());

        switch (event.getType()) {
            case "deposito":
                newAmount = account.getTotalAmount() + event.getAmount();
                break;
        
            case "retiro":
                newAmount = account.getTotalAmount() - event.getAmount();
                break;
        }

        account.setTotalAmount(newAmount);

        accountService.save(account);

    }
    
}