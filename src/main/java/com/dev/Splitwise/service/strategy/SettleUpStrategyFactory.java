package com.dev.Splitwise.service.strategy;

import com.dev.Splitwise.constant.SettleUpStrategies;

public class SettleUpStrategyFactory {
    public static settleUpStrategy getSettleUpStrategyFactory(SettleUpStrategies strategy) {

        return switch (strategy) {
            case MINIMUM_TRANSACTION_STRATEGY ->
                    new MinimumTransactionSettlementStrategy();

            default -> throw new IllegalArgumentException("Invalid strategy: " + strategy);
        };
    }

}
