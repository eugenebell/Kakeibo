package com.eugene.kakeibo.controller;

import com.eugene.kakeibo.model.Transaction;
import com.eugene.kakeibo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transactions";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "transaction-form";
    }
    
    @PostMapping
    public String createTransaction(@ModelAttribute Transaction transaction) {
        transactionService.createTransaction(transaction);
        return "redirect:/transactions";
    }
    
    @GetMapping("/{id}")
    public String getTransactionById(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "transaction-detail";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "transaction-form";
    }
    
    @PostMapping("/{id}")
    public String updateTransaction(@PathVariable Long id, @ModelAttribute Transaction transaction) {
        transaction.setId(id);
        transactionService.updateTransaction(transaction);
        return "redirect:/transactions";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }
}