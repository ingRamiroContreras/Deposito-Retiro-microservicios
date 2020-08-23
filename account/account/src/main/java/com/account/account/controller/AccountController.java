package com.account.account.controller;

import java.util.List;

import com.account.account.entity.Account;
import com.account.account.service.IAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private IAccountService service;

    @GetMapping("/listar")
    public List<Account> listar(){
        return service.findAll();
    }

    @GetMapping("/ver/{id}")
    public Account detalle(@PathVariable Integer id){

        Account account = service.findById(id);

        return account;
    }
    
}