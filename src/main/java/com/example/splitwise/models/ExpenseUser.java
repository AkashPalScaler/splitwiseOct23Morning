package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpenseUser extends BaseModel {
    @ManyToOne
    private User user;
    @ManyToOne
    private Expense expense;
    private Integer amount;
    @Enumerated(EnumType.STRING)
    private ExpenseUserType expenseUserType;
}
