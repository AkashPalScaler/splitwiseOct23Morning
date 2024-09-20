package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private String description;
    private Integer amount;

    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER)
    private List<ExpenseUser> expenseUsers;
    @ManyToOne
    private Group group;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
}

// A -> B (500) - NORMAL EPXENSE
// B -> A (500 cash) - PAYMENT EXPENSE