package com.eugene.kakeibo.controller;

import com.eugene.kakeibo.model.Budget;
import com.eugene.kakeibo.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/budgets")
public class BudgetController {
    
    @Autowired
    private BudgetService budgetService;
    
    @GetMapping
    public String getAllBudgets(Model model) {
        List<Budget> budgets = budgetService.getAllBudgets();
        model.addAttribute("budgets", budgets);
        return "budgets";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("budget", new Budget());
        return "budget-form";
    }
    
    @PostMapping
    public String createBudget(@ModelAttribute Budget budget) {
        budgetService.createBudget(budget);
        return "redirect:/budgets";
    }
    
    @GetMapping("/{id}")
    public String getBudgetById(@PathVariable Long id, Model model) {
        Budget budget = budgetService.getBudgetById(id);
        model.addAttribute("budget", budget);
        return "budget-detail";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Budget budget = budgetService.getBudgetById(id);
        model.addAttribute("budget", budget);
        return "budget-form";
    }
    
    @PostMapping("/{id}")
    public String updateBudget(@PathVariable Long id, @ModelAttribute Budget budget) {
        budget.setId(id);
        budgetService.updateBudget(budget);
        return "redirect:/budgets";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return "redirect:/budgets";
    }
}