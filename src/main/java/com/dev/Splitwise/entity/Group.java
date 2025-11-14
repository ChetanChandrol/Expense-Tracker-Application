package com.dev.Splitwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "SplitwiseGroup")
public class Group extends BaseModel {
    private String name;
    private double totalAmountSpent;
    @ManyToMany
//    @JsonManagedReference
    private List<User> members;
    @OneToMany
    private List<Expense> expense;
    @OneToMany
    private List<SettlementTransaction> settlementTransactions;
    @ManyToOne
    private User createdBy;
}
