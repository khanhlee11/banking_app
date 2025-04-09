package com.example.bankapp.controller;

import com.example.bankapp.model.BankAccount;
import com.example.bankapp.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/{userId}")
    public BankAccount createAccount(@PathVariable Long userId) {
        return bankAccountService.createAccount(userId);
    }

    @GetMapping("/{userId}")
    public List<BankAccount> getAccounts(@PathVariable Long userId) {
        return bankAccountService.getAccountsByUser(userId);
    }
}

