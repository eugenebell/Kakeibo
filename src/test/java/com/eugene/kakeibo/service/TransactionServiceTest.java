package com.eugene.kakeibo.service;

import com.eugene.kakeibo.model.Transaction;
import com.eugene.kakeibo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        testTransaction = new Transaction();
        testTransaction.setId(1L);
        testTransaction.setDescription("Test Transaction");
        testTransaction.setAmount(new BigDecimal("100.00"));
        testTransaction.setType(Transaction.TransactionType.EXPENSE);
        testTransaction.setDate(LocalDate.now());
        testTransaction.setCategory("Food & Dining");
    }

    @Test
    void getAllTransactions_ShouldReturnAllTransactions() {
        // Given
        List<Transaction> transactions = Arrays.asList(testTransaction);
        when(transactionRepository.findAll()).thenReturn(transactions);

        // When
        List<Transaction> result = transactionService.getAllTransactions();

        // Then
        assertEquals(1, result.size());
        assertEquals(testTransaction, result.get(0));
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void getTransactionById_ShouldReturnTransaction_WhenTransactionExists() {
        // Given
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(testTransaction));

        // When
        Transaction result = transactionService.getTransactionById(1L);

        // Then
        assertNotNull(result);
        assertEquals(testTransaction.getId(), result.getId());
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    void getTransactionById_ShouldReturnNull_WhenTransactionDoesNotExist() {
        // Given
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Transaction result = transactionService.getTransactionById(1L);

        // Then
        assertNull(result);
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    void createTransaction_ShouldSaveAndReturnTransaction() {
        // Given
        when(transactionRepository.save(testTransaction)).thenReturn(testTransaction);

        // When
        Transaction result = transactionService.createTransaction(testTransaction);

        // Then
        assertEquals(testTransaction, result);
        verify(transactionRepository, times(1)).save(testTransaction);
    }

    @Test
    void updateTransaction_ShouldSaveAndReturnTransaction() {
        // Given
        when(transactionRepository.save(testTransaction)).thenReturn(testTransaction);

        // When
        Transaction result = transactionService.updateTransaction(testTransaction);

        // Then
        assertEquals(testTransaction, result);
        verify(transactionRepository, times(1)).save(testTransaction);
    }

    @Test
    void deleteTransaction_ShouldDeleteTransaction() {
        // When
        transactionService.deleteTransaction(1L);

        // Then
        verify(transactionRepository, times(1)).deleteById(1L);
    }
}