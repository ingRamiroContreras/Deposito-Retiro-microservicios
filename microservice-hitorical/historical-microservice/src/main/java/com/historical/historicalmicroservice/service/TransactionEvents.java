package com.historical.historicalmicroservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.historical.historicalmicroservice.entity.Transaction;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionEvents {

    @Autowired
    private ItransactionService service;

    @Autowired
    ObjectMapper objectMapper;

    public void processTransactionEvent(ConsumerRecord<Integer, String> consumerRecord)
            throws JsonMappingException, JsonProcessingException {
     
        Transaction event = objectMapper.readValue(consumerRecord.value(), Transaction.class);

        service.save(event);

    }
    
}