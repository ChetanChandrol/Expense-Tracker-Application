package com.dev.Splitwise.service;

import com.dev.Splitwise.entity.Expense;

public interface ExpenseService {
    public Expense addExpense (Expense expense , int groupId);
    public Expense updateExpense (Expense expense , int groupId);
    public boolean deleteExpense (int expenseId , int groupId);
}
