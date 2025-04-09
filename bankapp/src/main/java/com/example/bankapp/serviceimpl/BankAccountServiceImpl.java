package com.example.bankapp.serviceimpl;

import com.example.bankapp.model.BankAccount;
import com.example.bankapp.model.User;
import com.example.bankapp.repository.BankAccountRepository;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    @Override
    public BankAccount createAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount account = new BankAccount();
        account.setAccountNumber(generateSafeAccountNumber());
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);

        return bankAccountRepository.save(account);
    }

    @Override
    public List<BankAccount> getAccountsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return bankAccountRepository.findByUser(user);
    }

    private String generateSafeAccountNumber() {
        String number;
        do {
            number = String.valueOf(1000000000L + (long)(Math.random() * 9000000000L));
        } while (bankAccountRepository.existsByAccountNumber(number));
        return number;
    }
}
