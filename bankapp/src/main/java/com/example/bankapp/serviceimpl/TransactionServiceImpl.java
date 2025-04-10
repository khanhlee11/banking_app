package com.example.bankapp.serviceimpl;

import com.example.bankapp.dto.BankAccountDTO;
import com.example.bankapp.dto.TransactionDTO;
import com.example.bankapp.model.BankAccount;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.repository.BankAccountRepository;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    @Override
    public TransactionDTO transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        BankAccount from = bankAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("From account not found"));
        BankAccount to = bankAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        bankAccountRepository.save(from);
        bankAccountRepository.save(to);

        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setFromAccount(from);
        transaction.setToAccount(to);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return mapToDTO(savedTransaction);
    }

    @Override
    public List<TransactionDTO> getTransactionHistory(Long accountId) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        List<Transaction> transactions = transactionRepository.findByFromAccountOrToAccount(account, account);
        return transactions.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private TransactionDTO mapToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setType(transaction.getType());
        dto.setAmount(transaction.getAmount());
        dto.setTimestamp(transaction.getTimestamp());

        BankAccountDTO fromDto = new BankAccountDTO();
        fromDto.setId(transaction.getFromAccount().getId());
        fromDto.setAccountNumber(transaction.getFromAccount().getAccountNumber());
        fromDto.setBalance(transaction.getFromAccount().getBalance());
        dto.setFromAccount(fromDto);

        BankAccountDTO toDto = new BankAccountDTO();
        toDto.setId(transaction.getToAccount().getId());
        toDto.setAccountNumber(transaction.getToAccount().getAccountNumber());
        toDto.setBalance(transaction.getToAccount().getBalance());
        dto.setToAccount(toDto);

        return dto;
    }
}
