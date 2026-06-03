package com.eugene.kakeibo.controller;

import com.eugene.kakeibo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class DashboardController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Get basic financial data
        BigDecimal totalIncome = transactionService.getTotalIncome();
        BigDecimal totalExpenses = transactionService.getTotalExpenses();
        BigDecimal remainingBalance = totalIncome.subtract(totalExpenses);

        // Set attributes for the view
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("remainingBalance", remainingBalance);
        model.addAttribute("monthlyBudgetAmount", new BigDecimal("4000.00"));
        model.addAttribute("monthlySpentAmount", new BigDecimal("3200.00"));
        model.addAttribute("monthlyRemainingAmount", new BigDecimal("800.00"));

        return "dashboard";
    }

    @GetMapping("/budgets")
    public String budgets() {
        return "budgets";
    }

    @GetMapping("/transactions")
    public String transactions() {
        return "transactions";
    }
}