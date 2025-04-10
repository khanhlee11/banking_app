package com.example.bankapp.service;

import com.example.bankapp.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    TransactionDTO transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
    List<TransactionDTO> getTransactionHistory(Long accountId);
}
