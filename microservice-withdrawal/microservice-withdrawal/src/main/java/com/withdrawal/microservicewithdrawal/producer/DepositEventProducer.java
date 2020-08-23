package com.withdrawal.microservicewithdrawal.producer;

import java.util.List;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.withdrawal.microservicewithdrawal.domain.Transaction;
import com.withdrawal.microservicewithdrawal.domain.TransactionRedis;
import com.withdrawal.microservicewithdrawal.service.ITransactionService;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Configuration
public class DepositEventProducer {

    String topic = "transaction-events";
    private Logger log = LoggerFactory.getLogger(DepositEventProducer.class);

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ITransactionService service;

    public ListenableFuture<SendResult<Integer, String>> sendDepositEvent(Transaction depositEvent)
            throws JsonProcessingException {

        Integer key = depositEvent.getId();
        String value = objectMapper.writeValueAsString(depositEvent);

        ProducerRecord<Integer, String> produceRecord = buildProducerRecord(key, value, topic);

        ListenableFuture<SendResult<Integer, String>> listeneable = kafkaTemplate.send(produceRecord);

        listeneable.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                try {
                    handleSuccess(key, value, result);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);

            }

        });
        return listeneable;
    }

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {

        List<Header> recordHeader = List.of(new RecordHeader("deposit-event-source", "scanner".getBytes()));

        return new ProducerRecord<Integer, String>(topic, null, key, value);

    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error sending message and thhe exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable e) {
            log.error("Error in on failure {}", e.getMessage());
        }

    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result)
            throws JsonMappingException, JsonProcessingException {

        TransactionRedis transaction = objectMapper.readValue(value, TransactionRedis.class);
        service.save(transaction);
        
        log.info("Message send Success Fully for the key : {} and the value is : {} , partition is {}", key, value,
                result.getRecordMetadata().partition());

                

    }

}