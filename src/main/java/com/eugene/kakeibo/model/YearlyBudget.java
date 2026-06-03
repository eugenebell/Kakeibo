package com.eugene.kakeibo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Year;

@Entity
@Table(name = "yearly_budgets")
public class YearlyBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "budget_year")
    private Year budgetYear;
    
    @Column(name = "budget_amount")
    private BigDecimal amount;
    
    @Column(name = "spent_amount")
    private BigDecimal spentAmount = BigDecimal.ZERO;
    
    // Constructors
    public YearlyBudget() {}
    
    public YearlyBudget(Year budgetYear, BigDecimal amount) {
        this.budgetYear = budgetYear;
        this.amount = amount;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Year getBudgetYear() {
        return budgetYear;
    }
    
    public void setBudgetYear(Year budgetYear) {
        this.budgetYear = budgetYear;
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
    
    public BigDecimal getRemainingAmount() {
        return amount.subtract(spentAmount);
    }
}