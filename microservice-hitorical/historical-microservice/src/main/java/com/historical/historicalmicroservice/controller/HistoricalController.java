package com.historical.historicalmicroservice.controller;

import java.util.List;

import com.historical.historicalmicroservice.entity.Transaction;
import com.historical.historicalmicroservice.service.ItransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoricalController {
    
    @Autowired
	private ItransactionService transaction;
	
	@GetMapping("/transaction/{accountId}")
	public ResponseEntity<?> obtenerByAccountId (@PathVariable Integer accountId){
		
		Iterable<Transaction> iTransaction=transaction.findByAccountId(accountId);
		
		return ResponseEntity.ok(iTransaction);
		
	}
	
	
	@GetMapping("/listar")
	public List<Transaction> listar(){
		
		return (List<Transaction>) transaction.findAll();
	}
	

}