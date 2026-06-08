package com.eugene.kakeibo.service;

import com.eugene.kakeibo.model.Transaction;
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
    
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
    
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
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
        List<Transaction> transactions = transactionRepository.findByType(Transaction.TransactionType.EXPENSE);
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    // Helper method to calculate monthly spent amount
    public BigDecimal getMonthlySpentAmount(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1); // Last day of the month
        
        List<Transaction> monthlyTransactions = transactionRepository.findByDateBetween(startDate, endDate);
        return monthlyTransactions.stream()
                .filter(transaction -> transaction.getType() == Transaction.TransactionType.EXPENSE)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}