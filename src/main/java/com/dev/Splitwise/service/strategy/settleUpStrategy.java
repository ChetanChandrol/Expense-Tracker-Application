package com.dev.Splitwise.service.strategy;

import com.dev.Splitwise.entity.Expense;
import com.dev.Splitwise.entity.SettlementTransaction;

import java.util.List;

public interface settleUpStrategy {
    List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses);
}
