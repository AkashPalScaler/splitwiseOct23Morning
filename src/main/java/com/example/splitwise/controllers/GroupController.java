package com.example.splitwise.controllers;

import com.example.splitwise.dtos.ResponseStatus;
import com.example.splitwise.dtos.SettleGroupRequestDTO;
import com.example.splitwise.dtos.SettleGroupResponseDTO;
import com.example.splitwise.dtos.Transaction;
import com.example.splitwise.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.*;

@Controller
public class GroupController {
    @Autowired
    GroupService groupService;

    public SettleGroupResponseDTO settleUp(SettleGroupRequestDTO requestDTO){
        SettleGroupResponseDTO responseDTO = new SettleGroupResponseDTO();
        try{
            List<Transaction> transactions = groupService.settleUp(requestDTO.getGroup_id());
            responseDTO.setTransactions(transactions);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("Please complete the transactions to settle up the group");

        }catch(Exception e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }
}
