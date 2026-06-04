package com.eugene.kakeibo.service;

import com.eugene.kakeibo.model.Transaction;
import com.eugene.kakeibo.model.Transaction.TransactionType;
import com.eugene.kakeibo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByDateBetween(startDate, endDate);
    }

    public BigDecimal getTotalIncome() {
        List<Transaction> transactions = transactionRepository.findByType(Transaction.TransactionType.INCOME);
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenses() {
        List<Transaction> transactions = transactionRepository.findByType(TransactionType.EXPENSE);
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}