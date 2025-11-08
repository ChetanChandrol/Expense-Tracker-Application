package com.dev.Splitwise.entity;

import com.dev.Splitwise.constant.UserExpenseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserExpense extends BaseModel{
    @ManyToOne
    private User user;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;
}
