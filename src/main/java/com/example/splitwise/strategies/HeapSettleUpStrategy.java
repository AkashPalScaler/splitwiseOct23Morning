package com.example.splitwise.strategies;

import com.example.splitwise.dtos.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
class Pair implements Comparable<Pair>{
    Integer amount;
    Long userId;

    public Pair(int amount, Long userId) {
        this.amount = amount;
        this.userId = userId;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.amount <= o.amount){
            return -1;
        }
        return 1;
    }
}
@Component
public class HeapSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUp(HashMap<Long, Integer> balanceMap) {
        // Create two heaps
        PriorityQueue<Pair> giver = new PriorityQueue<>();
        PriorityQueue<Pair> receiver = new PriorityQueue<>();

        // Sort the postivie and negative amounts into these two heaps
        for(Long user_id : balanceMap.keySet()){
            Integer amount = balanceMap.get(user_id);
            if(amount > 0){
                receiver.add(new Pair(amount, user_id));
            }else{
                giver.add(new Pair(-1*amount , user_id));
            }
        }
        System.out.println("DEBUG");
        // Then go through the max/min on each heap item and create transactions
        List<Transaction> transactions = new ArrayList<>();

        while(giver.size() > 0 && receiver.size() > 0){
            Pair maxReceive = receiver.poll();
            Pair maxGive = giver.poll();

            if(maxReceive.amount > maxGive.amount){ // Give lesser than receive
                transactions.add(new Transaction(maxGive.userId, maxReceive.userId,maxGive.amount));
                receiver.add(new Pair(maxReceive.amount - maxGive.amount, maxReceive.userId));
            }else if(maxReceive.amount < maxGive.amount){ // Give greater than receive
                transactions.add(new Transaction(maxGive.userId, maxReceive.userId, maxReceive.amount));
                giver.add(new Pair(maxGive.amount- maxReceive.amount, maxGive.userId));
            }else{
                transactions.add(new Transaction(maxGive.userId, maxReceive.userId, maxGive.amount));
            }
        }

        System.out.println("DEBUG");

        return transactions;
    }
}
