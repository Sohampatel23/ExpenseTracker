package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping("/addExpense")
    public String addExpense(@RequestBody Expense expense)
    {
        return expenseService.addExpense(expense);
    }

    @GetMapping("/byDate/{date}")
    public List<Expense> getExpensesByDate(@PathVariable String date) {

        return (List<Expense>) expenseService.findByDate(date);

    }

    @GetMapping("/month/{month}")
    public double getExpensesByMonth(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate month) {

        return expenseService.findByMonth(month);

    }

    @GetMapping("/expenses")
    public List<Expense> getExpensesForLast3Months() {
       return expenseService.expenseOfMonths();
    }
}
