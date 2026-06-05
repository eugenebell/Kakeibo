package com.eugene.kakeibo.service;

import com.eugene.kakeibo.model.Budget;
import com.eugene.kakeibo.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    
    @Autowired
    private BudgetRepository budgetRepository;
    
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
    
    public Budget getBudgetById(Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        return budget.orElse(null);
    }
    
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
    
    public Budget updateBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
    
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}