package com.eugene.kakeibo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "monthly_budgets")
public class MonthlyBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "budget_date")
    private LocalDate date;
    
    @Column(name = "budget_amount")
    private BigDecimal amount;
    
    @Column(name = "spent_amount")
    private BigDecimal spentAmount = BigDecimal.ZERO;
    
    @Column(name = "budget_id")
    private Long budgetId;
    
    // Constructors
    public MonthlyBudget() {}
    
    public MonthlyBudget(LocalDate date, BigDecimal amount, Long budgetId) {
        this.date = date;
        this.amount = amount;
        this.budgetId = budgetId;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getSpentAmount() {
        return spentAmount;
    }
    
    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
    }
    
    public Long getBudgetId() {
        return budgetId;
    }
    
    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }
    
    public BigDecimal getRemainingAmount() {
        return amount.subtract(spentAmount);
    }
}