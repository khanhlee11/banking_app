package com.example.bankapp.service;

import com.example.bankapp.model.BankAccount;

import java.util.List;

public interface BankAccountService {
    BankAccount createAccount(Long userId);
    List<BankAccount> getAccountsByUser(Long userId);
}
