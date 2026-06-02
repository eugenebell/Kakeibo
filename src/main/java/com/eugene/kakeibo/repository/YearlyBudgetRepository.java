package com.eugene.kakeibo.repository;

import com.eugene.kakeibo.model.YearlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlyBudgetRepository extends JpaRepository<YearlyBudget, Long> {
}