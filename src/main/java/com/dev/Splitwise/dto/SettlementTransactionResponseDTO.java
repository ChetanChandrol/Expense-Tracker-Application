package com.dev.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SettlementTransactionResponseDTO {
    private UserResponseDTO lender ;
    private UserResponseDTO borrower;
    private double amount;
}
