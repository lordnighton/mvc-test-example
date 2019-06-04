package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping(value = "accounts")
    public String getAllAccounts() {
        return accountService.getAccount();
    }

}