package com.example.bankapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccountDTO {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
}

