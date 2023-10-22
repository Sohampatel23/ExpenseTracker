package com.example.ExpenseTracker.repo;

import com.example.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseRepo extends JpaRepository<Expense,Integer> {

    List<Expense> findByDate(LocalDate date);

    List<Expense> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
