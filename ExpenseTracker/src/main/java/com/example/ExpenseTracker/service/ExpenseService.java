package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repo.IExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    IExpenseRepo iExpenseRepo;

    public Object findByDate(String date) {

        LocalDate requestedDate = LocalDate.parse(date);

        List<Expense> expenses = iExpenseRepo.findByDate(requestedDate);
        return expenses;
    }

    public double findByMonth(LocalDate month) {

        LocalDate firstDayOfMonth = month.withDayOfMonth(1);
        LocalDate lastDayOfMonth = month.withDayOfMonth(month.lengthOfMonth());

        List<Expense> expenses = iExpenseRepo.findAllByDateBetween(firstDayOfMonth, lastDayOfMonth);

        double totalExpenses = expenses.stream().mapToDouble(Expense::getPrice).sum();
        return totalExpenses;
    }

    public List<Expense> expenseOfMonths() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(3);

        // Use the start and end date to query expenses from the database
        List<Expense> expenses = iExpenseRepo.findAllByDateBetween(startDate, endDate);

        return expenses;
    }

    public String addExpense(Expense expense) {
        iExpenseRepo.save(expense);
        return "Expense Added";
    }


}
