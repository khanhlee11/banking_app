package com.example.bankapp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private String type;
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private BankAccountDTO fromAccount;
    private BankAccountDTO toAccount;
}
