package com.example.splitwise.services;

import com.example.splitwise.dtos.Transaction;
import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseUser;
import com.example.splitwise.models.ExpenseUserType;
import com.example.splitwise.models.Group;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.strategies.HeapSettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    HeapSettleUpStrategy heapSettleUpStrategy;
    public List<Transaction> settleUp(Long group_id){
        // fetch group using the group id
        Optional<Group> groupOpt = groupRepository.findById(group_id);

        if(groupOpt.isEmpty()){
            throw new RuntimeException("Group is invalid");
        }

        Group group = groupOpt.get();

        // fetch the expense list (If fetchType is LAZY)
        // List<Expense> expenses = expenseRepository.findAllByGroupId(group_id)

        // If fetchType = EAGER
        List<Expense> expenses = group.getExpenses(); // Run the exception table query at this point of time in case of LAZY

        // create the balance map
        HashMap<Long, Integer> balanceMap = new HashMap<>();

        for(Expense expense: expenses){
            List<ExpenseUser> expenseUsers = expense.getExpenseUsers();
            System.out.println("DEBUG");
            for(ExpenseUser expenseUser: expenseUsers){

                Long user_id = expenseUser.getUser().getId();
                balanceMap.putIfAbsent(user_id, 0);

                if(expenseUser.getExpenseUserType().equals(ExpenseUserType.PAID)){
                    balanceMap.put(user_id, balanceMap.get(user_id) + expenseUser.getAmount());
                }else{
                    balanceMap.put(user_id, balanceMap.get(user_id) - expenseUser.getAmount());
                }
            }
        }
        System.out.println("DEBUG");

        // Then we use heap to create the transactions

        return heapSettleUpStrategy.settleUp(balanceMap);
    }
}
// EXP1 :A:2000 | A: 500, B: 500, C: 500, D:500   --> (EXP1,A,2000, PAID), (EXP1,A,500, HAD),
//                                                     (EXP1,B,500, HAD), (EXP1,C,500, HAD), (EXP1,D,500, HAD)
// EXP2 :A;1000, B:1000 | A: 800, B: 200, C: 500, D:500

// A : +2000 -500