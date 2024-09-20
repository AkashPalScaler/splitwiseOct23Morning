package com.example.splitwise.command;

import com.example.splitwise.controllers.GroupController;
import com.example.splitwise.dtos.SettleGroupRequestDTO;
import com.example.splitwise.dtos.SettleGroupResponseDTO;
import com.example.splitwise.dtos.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettleUpGroupCommand implements Command{
    @Autowired
    GroupController groupController;
    @Override
    public void execute(String input) {
        //settleUpGroup u1 g1
        String[] s = input.split(" ");
        Long groupId = Long.parseLong(s[2]);
        SettleGroupRequestDTO requestDTO = new SettleGroupRequestDTO();
        requestDTO.setGroup_id(groupId);
        SettleGroupResponseDTO responseDTO = groupController.settleUp(requestDTO);
        System.out.println(responseDTO.getMessage());
        for(Transaction transaction : responseDTO.getTransactions()){
            System.out.println(transaction.getFromUserId() + "has to pay " + transaction.getToUserId() + " : " + transaction.getAmount());
        }
    }

    @Override
    public boolean matches(String input) {
        String[] s = input.split(" ");
        return s[0].equalsIgnoreCase("settleUpGroup");
    }
}
