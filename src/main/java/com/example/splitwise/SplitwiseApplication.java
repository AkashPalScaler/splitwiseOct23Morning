package com.example.splitwise;

import com.example.splitwise.command.SettleUpGroupCommand;
import com.example.splitwise.controllers.GroupController;
import com.example.splitwise.dtos.SettleGroupRequestDTO;
import com.example.splitwise.dtos.SettleGroupResponseDTO;
import com.example.splitwise.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {
	@Autowired
	DataGenerator dataGenerator;
	@Autowired
	SettleUpGroupCommand settleUpGroupCommand;
	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		dataGenerator.generateData();
        settleUpGroupCommand.execute("settleUpGroup 1 1");
	}
}

// Agenda
// Setting up the project, coding models, attaching db and creating tables
// Command registry
// Controllers, settleUpcommnad, dataGeneration