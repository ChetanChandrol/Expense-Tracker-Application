package com.dev.Splitwise.service;

import com.dev.Splitwise.entity.Expense;
import com.dev.Splitwise.entity.Group;
import com.dev.Splitwise.entity.UserExpense;
import com.dev.Splitwise.exception.GroupNotFoundException;
import com.dev.Splitwise.repository.ExpenseRepository;
import com.dev.Splitwise.repository.GroupRepository;
import com.dev.Splitwise.repository.UserExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImplementation implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserExpenseRepository userExpenseRepository;

    @Override
    public Expense addExpense(Expense expense, int groupId) {

        Group currentGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group does not exist for id " + groupId));
        int addedByUserId = expense.getAddedBy().getId();

        for(UserExpense userExpense : expense.getUserExpenses())
        {
            if (userExpense.getUser() == null || userExpense.getAmount() == null)
            {
                throw new RuntimeException("Invalid User Expense");
            }
            if (userExpense.getUser().getId()==addedByUserId)
            {
                userExpense.setAmount(expense.getAmount()-userExpense.getAmount());
            }
        }
        List<UserExpense> savedUserExpense = userExpenseRepository.saveAll(expense.getUserExpenses());
        expense.setUserExpenses(savedUserExpense);
        Expense savedExpense = expenseRepository.save(expense);

        if (currentGroup.getExpense()!=null) {
            currentGroup.getExpense().add(savedExpense);
        }
        else {
            List<Expense> expenses = new ArrayList<>();
            expenses.add(savedExpense);
            currentGroup.setExpense(expenses);

        }
        groupRepository.save(currentGroup);

        return savedExpense;
    }

    @Override
    public Expense updateExpense(Expense expense, int groupId) {

        if (!expenseRepository.existsById(expense.getId()))
        {
            throw new RuntimeException("Expense doesn't exist with id"+expense.getId());
        }
        Group currentGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group does not exist for id " + groupId));


        int addedByUserId = expense.getAddedBy().getId();

        for(UserExpense userExpense : expense.getUserExpenses())
        {
            if (userExpense.getUser() == null || userExpense.getAmount() == null)
            {
                throw new RuntimeException("Invalid User Expense");
            }
            if (userExpense.getUser().getId()==addedByUserId)
            {
                userExpense.setAmount(expense.getAmount()-userExpense.getAmount());
            }
        }
        List<UserExpense> savedUserExpense = userExpenseRepository.saveAll(expense.getUserExpenses());
        expense.setUserExpenses(savedUserExpense);
        Expense savedExpense = expenseRepository.save(expense);

        if (currentGroup.getExpense()!=null) {
            currentGroup.getExpense().add(savedExpense);
        }
        else {
            List<Expense> expenses = new ArrayList<>();
            expenses.add(savedExpense);
            currentGroup.setExpense(expenses);

        }
        groupRepository.save(currentGroup);

        return savedExpense;

    }

    @Override
    public boolean deleteExpense(int expenseId, int groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new RuntimeException("Group doesn't exist with id" + groupId);
        }
        if (!expenseRepository.existsById(expenseId)) {
            throw new RuntimeException("Expense does not exist with id" + expenseId);
        }
        expenseRepository.deleteById(expenseId);
        return true;
    }
}
