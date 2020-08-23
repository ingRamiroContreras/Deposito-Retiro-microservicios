package com.historical.historicalmicroservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.historical.historicalmicroservice.service.TransactionEvents;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumerListener {

    @Autowired
    private TransactionEvents transactionEvents;

    private Logger log = LoggerFactory.getLogger(TransactionEventConsumerListener.class);

    @KafkaListener(topics = { "transaction-events" })
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord)
            throws JsonMappingException, JsonProcessingException {

		log.info("ConsumerRecord :{}", consumerRecord.value());

		transactionEvents.processTransactionEvent(consumerRecord);
		
	}


}