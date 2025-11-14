package com.dev.Splitwise.dto;

import com.dev.Splitwise.entity.Expense;
import com.dev.Splitwise.entity.SettlementTransaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetGroupResponseDTO {
    private int groupId;
    private String groupName;
    private UserResponseDTO createdBy;
    private List<UserResponseDTO> members;
    private List<Expense> expense;
    private List<SettlementTransaction> settlementTransactions;
}
