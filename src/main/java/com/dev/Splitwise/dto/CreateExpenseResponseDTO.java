package com.dev.Splitwise.dto;

import com.dev.Splitwise.entity.UserExpense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CreateExpenseResponseDTO {
    private int expenseId;
    private String description;
    private Double amount;
    private UserResponseDTO addedBy;
    private List<UserExpenseResponseDTO> userExpense;

}
