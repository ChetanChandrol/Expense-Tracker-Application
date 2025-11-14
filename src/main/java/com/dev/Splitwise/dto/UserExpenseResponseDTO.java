package com.dev.Splitwise.dto;

import com.dev.Splitwise.constant.UserExpenseType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserExpenseResponseDTO {
    private UserResponseDTO user;
    private Double amount;
    private UserExpenseType userExpenseType;
}
