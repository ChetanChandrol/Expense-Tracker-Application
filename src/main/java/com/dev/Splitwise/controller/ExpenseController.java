package com.dev.Splitwise.controller;

import com.dev.Splitwise.entity.Expense;
import com.dev.Splitwise.mapper.EntityDtoMapper;
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
        Expense savedExpense = expenseService.addExpense(expense, groupId);
        return ResponseEntity.ok(EntityDtoMapper.toCreateExpenseResponseDTO(savedExpense));
    }

    @PutMapping("/update-expense/{groupId}")
    public ResponseEntity updateExpense(@RequestBody Expense expense, @PathVariable int groupId) {
        if (expense.getUserExpenses() == null || expense.getUserExpenses().isEmpty()) {
            throw new RuntimeException("User expense cannot be null or empty");
        }
        if (expense.getAmount() == null || expense.getAmount() == 0) {
            throw new RuntimeException("Expense amount cannot be null or zero");
        }
        Expense savedExpense = expenseService.updateExpense(expense, groupId);
        return ResponseEntity.ok(EntityDtoMapper.toCreateExpenseResponseDTO(savedExpense));
    }

    @DeleteMapping("/delete-expense/{expenseId}/{groupId}")
    public ResponseEntity deleteExpense(@PathVariable int expenseId, @PathVariable int groupId) {
        Boolean b = expenseService.deleteExpense(expenseId, groupId);
        return ResponseEntity.ok("EXPENSE DELETED SUCCESSFULLY");
    }

    @GetMapping("/get-expense/{expenseId}")
    public ResponseEntity getExpense(@PathVariable int expenseId)
        {
           Expense expense =  expenseService.getExpense(expenseId);
            return ResponseEntity.ok(EntityDtoMapper.toCreateExpenseResponseDTO(expense));
        }

    }




