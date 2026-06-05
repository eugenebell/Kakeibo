package com.eugene.kakeibo.controller;

import com.eugene.kakeibo.model.Budget;
import com.eugene.kakeibo.model.Transaction;
import com.eugene.kakeibo.service.BudgetService;
import com.eugene.kakeibo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private BudgetService budgetService;
    
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Get all budgets for display
        List<Budget> budgets = budgetService.getAllBudgets();
        model.addAttribute("budgets", budgets);
        
        // Get recent transactions
        List<Transaction> recentTransactions = transactionService.getAllTransactions();
        model.addAttribute("recentTransactions", recentTransactions);
        
        // Financial calculations
        BigDecimal totalIncome = transactionService.getTotalIncome();
        BigDecimal totalExpenses = transactionService.getTotalExpenses();
        BigDecimal remainingBalance = totalIncome != null && totalExpenses != null ? 
                totalIncome.subtract(totalExpenses) : BigDecimal.ZERO;
        
        // Set defaults if null
        model.addAttribute("totalIncome", totalIncome != null ? totalIncome : BigDecimal.ZERO);
        model.addAttribute("totalExpenses", totalExpenses != null ? totalExpenses : BigDecimal.ZERO);
        model.addAttribute("remainingBalance", remainingBalance);
        model.addAttribute("monthlyBudgetAmount", new BigDecimal("4000.00"));
        model.addAttribute("monthlySpentAmount", new BigDecimal("3200.00"));
        model.addAttribute("monthlyRemainingAmount", new BigDecimal("800.00"));
        
        return "dashboard";
    }
}