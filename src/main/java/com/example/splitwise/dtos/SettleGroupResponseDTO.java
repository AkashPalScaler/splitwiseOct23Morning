package com.example.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleGroupResponseDTO {
    private List<Transaction> transactions;
    private String message;
    private ResponseStatus responseStatus;
}
// from , to, amount
