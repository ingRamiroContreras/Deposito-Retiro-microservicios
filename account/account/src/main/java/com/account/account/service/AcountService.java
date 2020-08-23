package com.account.account.service;

import java.util.List;

import com.account.account.entity.Account;
import com.account.account.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcountService implements IAccountService {

    @Autowired
    AccountRepository accountRepo;


	@Override
	public List<Account> findAll() {
		return (List<Account>) accountRepo.findAll();
	}

	@Override
	public Account findById(Integer id) {
		return accountRepo.findById(id).orElse(null);
	}

	@Override
	public Account save(Account account) {
		return accountRepo.save(account);
	}
    
}