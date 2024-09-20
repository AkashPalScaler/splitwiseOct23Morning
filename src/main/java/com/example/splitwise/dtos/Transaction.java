package com.example.splitwise.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private Long fromUserId;
    private Long toUserId;
    private int amount;
}
