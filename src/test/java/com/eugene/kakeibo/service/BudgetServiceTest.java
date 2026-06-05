package com.eugene.kakeibo.service;

import com.eugene.kakeibo.model.Budget;
import com.eugene.kakeibo.repository.BudgetRepository;
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
class BudgetServiceTest {

    @Mock
    private BudgetRepository budgetRepository;

    @InjectMocks
    private BudgetService budgetService;

    private Budget testBudget;

    @BeforeEach
    void setUp() {
        testBudget = new Budget();
        testBudget.setId(1L);
        testBudget.setName("Test Budget");
        testBudget.setAmount(new BigDecimal("1000.00"));
        testBudget.setStartDate(LocalDate.now());
        testBudget.setEndDate(LocalDate.now().plusMonths(1));
        testBudget.setCategory("Food & Dining");
    }

    @Test
    void getAllBudgets_ShouldReturnAllBudgets() {
        // Given
        List<Budget> budgets = Arrays.asList(testBudget);
        when(budgetRepository.findAll()).thenReturn(budgets);

        // When
        List<Budget> result = budgetService.getAllBudgets();

        // Then
        assertEquals(1, result.size());
        assertEquals(testBudget, result.get(0));
        verify(budgetRepository, times(1)).findAll();
    }

    @Test
    void getBudgetById_ShouldReturnBudget_WhenBudgetExists() {
        // Given
        when(budgetRepository.findById(1L)).thenReturn(Optional.of(testBudget));

        // When
        Budget result = budgetService.getBudgetById(1L);

        // Then
        assertNotNull(result);
        assertEquals(testBudget.getId(), result.getId());
        verify(budgetRepository, times(1)).findById(1L);
    }

    @Test
    void getBudgetById_ShouldReturnNull_WhenBudgetDoesNotExist() {
        // Given
        when(budgetRepository.findById(1L)).thenReturn(Optional.empty());

        // When
        Budget result = budgetService.getBudgetById(1L);

        // Then
        assertNull(result);
        verify(budgetRepository, times(1)).findById(1L);
    }

    @Test
    void createBudget_ShouldSaveAndReturnBudget() {
        // Given
        when(budgetRepository.save(testBudget)).thenReturn(testBudget);

        // When
        Budget result = budgetService.createBudget(testBudget);

        // Then
        assertEquals(testBudget, result);
        verify(budgetRepository, times(1)).save(testBudget);
    }

    @Test
    void updateBudget_ShouldSaveAndReturnBudget() {
        // Given
        when(budgetRepository.save(testBudget)).thenReturn(testBudget);

        // When
        Budget result = budgetService.updateBudget(testBudget);

        // Then
        assertEquals(testBudget, result);
        verify(budgetRepository, times(1)).save(testBudget);
    }

    @Test
    void deleteBudget_ShouldDeleteBudget() {
        // When
        budgetService.deleteBudget(1L);

        // Then
        verify(budgetRepository, times(1)).deleteById(1L);
    }
}