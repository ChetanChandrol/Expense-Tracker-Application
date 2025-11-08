package com.dev.Splitwise.controller;

import com.dev.Splitwise.entity.Expense;
import com.dev.Splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add-expense/{groupId}")
    public ResponseEntity addExpense(@RequestBody Expense expense, @PathVariable int groupId) {
        if (expense.getUserExpenses() == null || expense.getUserExpenses().isEmpty()) {
            throw new RuntimeException("User expense cannot be null or empty");
        }
        if (expense.getAmount() == null || expense.getAmount() == 0) {
            throw new RuntimeException("Expense amount cannot be null or zero");
        }
        return ResponseEntity.ok(expenseService.addExpense(expense, groupId));
    }


    @PutMapping("/update-expense/{groupId}")
    public ResponseEntity updateExpense(@RequestBody Expense expense, @PathVariable int groupId) {
        if (expense.getUserExpenses() == null || expense.getUserExpenses().isEmpty()) {
            throw new RuntimeException("User expense cannot be null or empty");
        }
        if (expense.getAmount() == null || expense.getAmount() == 0) {
            throw new RuntimeException("Expense amount cannot be null or zero");
        }
        return ResponseEntity.ok(expenseService.updateExpense(expense, groupId));
    }

    @DeleteMapping("/delete-expense/{expenseId}/{groupId}")
    public ResponseEntity deleteExpense(@PathVariable int expenseId, @PathVariable int groupId) {
        return ResponseEntity.ok(deleteExpense(expenseId, groupId));

    }



}
