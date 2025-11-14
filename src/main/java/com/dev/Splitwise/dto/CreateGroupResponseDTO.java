package com.dev.Splitwise.dto;

import com.dev.Splitwise.entity.Expense;
import com.dev.Splitwise.entity.SettlementTransaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CreateGroupResponseDTO {
    private int groupId;
    private String groupName;
    private UserResponseDTO createdBy;
    private List<UserResponseDTO> members;
    private List<SettlementTransaction> settlementTransactions;
    private List<Expense> expenses;
}
