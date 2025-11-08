package com.dev.Splitwise.repository;

import com.dev.Splitwise.entity.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense,Integer> {
}
