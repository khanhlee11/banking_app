package com.example.bankapp.repository;

import com.example.bankapp.model.BankAccount;
import com.example.bankapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByUser(User user);

    boolean existsByAccountNumber(String accountNumber);
}
