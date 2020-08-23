package com.account.account.listener;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import com.account.account.service.TransactionEvents;

@Component
public class TransactionEventsConsumer {

	@Autowired
	private TransactionEvents transactionEvents;

	private Logger log = LoggerFactory.getLogger(TransactionEventsConsumer.class);

	@KafkaListener(topics = { "transaction-events" })
	public void onMessage(ConsumerRecord<Integer, String> consumerRecord) throws IOException {

		log.info("ConsumerRecord :{}", consumerRecord.value());

		transactionEvents.processTransactionEvent(consumerRecord);
		
	}

}



