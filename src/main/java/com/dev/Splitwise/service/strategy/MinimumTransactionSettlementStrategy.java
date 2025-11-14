package com.dev.Splitwise.service.strategy;

import com.dev.Splitwise.constant.UserExpenseType;
import com.dev.Splitwise.dto.UserAmount;
import com.dev.Splitwise.entity.Expense;
import com.dev.Splitwise.entity.SettlementTransaction;
import com.dev.Splitwise.entity.User;
import com.dev.Splitwise.entity.UserExpense;
import jakarta.persistence.Entity;

import java.util.*;

public class MinimumTransactionSettlementStrategy implements settleUpStrategy {

    private HashMap<User, Double> getOutStandingBalances(List<Expense> expenses) {
        HashMap<User, Double> expenseMap = new HashMap<>();
        for (Expense expense : expenses) {
            for (UserExpense userExpense : expense.getUserExpenses()) {
                User participant = userExpense.getUser();
                Double amount = userExpense.getAmount();
                if (expenseMap.containsKey(participant)) {
                    if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)) {
                        expenseMap.put(participant, expenseMap.get(participant) + amount);
                    } else {
                        expenseMap.put(participant, expenseMap.get(participant) - amount);
                    }
                } else {
                    if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)) {
                        expenseMap.put(participant, 0 + amount);
                    } else {
                        expenseMap.put(participant, 0 - amount);
                    }
                }
            }
        }
        return expenseMap;
    }

    @Override
    public List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses) {
        HashMap<User,Double> map = getOutStandingBalances(expenses);
        Comparator<UserAmount> minHeapComparator = Comparator.comparingDouble(UserAmount::getAmount);
        Comparator<UserAmount> maxHeapComparator = Comparator.comparingDouble(UserAmount::getAmount).reversed();
        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>(maxHeapComparator);
        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>(minHeapComparator);

        for (Map.Entry<User,Double> entry : map.entrySet()){
            if (entry.getValue()<0){
                minHeap.add(new UserAmount(entry.getKey(),entry.getValue()));
            } else if (entry.getValue()>0) {
                maxHeap.add(new UserAmount(entry.getKey(),entry.getValue()));
            }
            else {
                System.out.println("User Does not Need to participate in settlement");
            }

        }
        List<SettlementTransaction> settlementTransactions = new ArrayList<>();

        while (!maxHeap.isEmpty() && !minHeap.isEmpty())
        {
             UserAmount borrower = minHeap.poll();
             UserAmount lender = maxHeap.poll();

             if (Math.abs(borrower.getAmount())>lender.getAmount())
             {
                 borrower.setAmount(borrower.getAmount()+ lender.getAmount());
                 minHeap.add(borrower);
                 SettlementTransaction settlement = new SettlementTransaction(borrower.getUser(), lender.getUser(), lender.getAmount());
                 settlementTransactions.add(settlement);
             } else if (Math.abs(borrower.getAmount())<lender.getAmount()) {
                 lender.setAmount(lender.getAmount()+ borrower.getAmount());
                 maxHeap.add(lender);
                 SettlementTransaction settlement = new SettlementTransaction(borrower.getUser(), lender.getUser(), borrower.getAmount());
                settlementTransactions.add(settlement);
             }
             else {
                 System.out.println("Do nothing both are equal");
                 SettlementTransaction settlement = new SettlementTransaction(borrower.getUser(), lender.getUser(), lender.getAmount());
                 settlementTransactions.add(settlement);
             }
        }

        return settlementTransactions;
    }

}