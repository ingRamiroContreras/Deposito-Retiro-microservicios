package com.account.account.service;

import java.util.List;

import com.account.account.entity.Account;

public interface IAccountService {

    public List<Account> findAll(); 

    public Account findById(Integer id);

    public Account save(Account account);
    
}