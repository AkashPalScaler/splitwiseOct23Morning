package com.example.splitwise.strategies;

import com.example.splitwise.dtos.Transaction;

import java.util.HashMap;
import java.util.List;
public interface SettleUpStrategy {
    public List<Transaction>  settleUp(HashMap<Long, Integer> balanceMap);
}
