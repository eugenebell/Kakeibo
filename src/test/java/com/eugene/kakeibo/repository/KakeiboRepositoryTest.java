package com.eugene.kakeibo.repository;

import com.eugene.kakeibo.model.Budget;
import com.eugene.kakeibo.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class KakeiboRepositoryTest {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void budgetRepository_ShouldSaveAndRetrieveBudget() {
        // Given
        Budget budget = new Budget();
        budget.setName("Test Budget");
        budget.setAmount(new BigDecimal("1000.00"));
        budget.setStartDate(LocalDate.now());
        budget.setEndDate(LocalDate.now().plusMonths(1));
        budget.setCategory("Food & Dining");

        // When
        Budget savedBudget = budgetRepository.save(budget);
        Budget foundBudget = budgetRepository.findById(savedBudget.getId()).orElse(null);

        // Then
        assertNotNull(foundBudget);
        assertEquals(budget.getName(), foundBudget.getName());
        assertEquals(budget.getAmount(), foundBudget.getAmount());
    }

    @Test
    void transactionRepository_ShouldSaveAndRetrieveTransaction() {
        // Given
        Transaction transaction = new Transaction();
        transaction.setDescription("Test Transaction");
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setType(Transaction.TransactionType.EXPENSE);
        transaction.setDate(LocalDate.now());
        transaction.setCategory("Food & Dining");

        // When
        Transaction savedTransaction = transactionRepository.save(transaction);
        Transaction foundTransaction = transactionRepository.findById(savedTransaction.getId()).orElse(null);

        // Then
        assertNotNull(foundTransaction);
        assertEquals(transaction.getDescription(), foundTransaction.getDescription());
        assertEquals(transaction.getAmount(), foundTransaction.getAmount());
        assertEquals(transaction.getType(), foundTransaction.getType());
    }
}