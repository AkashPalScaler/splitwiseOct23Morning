package com.example.splitwise;

import com.example.splitwise.models.*;
import com.example.splitwise.repositories.ExpenseRepository;
import com.example.splitwise.repositories.ExpenseUserRepository;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataGenerator {
    @Autowired
    public UserRepository userRepositories;
    @Autowired
    public GroupRepository groupRepositories;
    @Autowired
    public ExpenseRepository expenseRepositories;
    @Autowired
    public ExpenseUserRepository expenseUserRepositories;

    public void generateData(){
        User user1 = new User();
        user1.setName("Akash");
        user1.setEmail("akash.pal95@gmail.com");
        user1.setPassword("passpass");

        userRepositories.save(user1);

        User user2 = new User();
        user2.setName("Sam");
        user2.setEmail("sam.pal95@gmail.com");
        user2.setPassword("passpass");

        userRepositories.save(user2);

        User user3 = new User();
        user3.setName("Rajesh");
        user3.setEmail("rajesh.95@gmail.com");
        user3.setPassword("passpass");

        userRepositories.save(user3);

        User user4 = new User();
        user4.setName("Amrita");
        user4.setEmail("amrita.pal95@gmail.com");
        user4.setPassword("passpass");

        userRepositories.save(user4);

        List<User> members = new ArrayList<>();
        members.add(user1);
        members.add(user2);
        members.add(user3);
        members.add(user4);



        //Creating a group
        Group group = new Group();
        group.setName("GoaTrip");
        group.setAdmin(user1);
        group.setMembers(members);
        List<Expense> expenses = new ArrayList<>();
        groupRepositories.save(group);

        //Creating an expense
        Expense ex1 = new Expense();
        ex1.setAmount(2000);
        ex1.setGroup(group);
        ex1.setDescription("Goa car rent");
        ex1.setExpenseType(ExpenseType.NORMAL);
        expenseRepositories.save(ex1);

        //List to store all expenseUsers
        List<ExpenseUser> expenseUsers = new ArrayList<>();

        //Creating expense users
        ExpenseUser eu1 = new ExpenseUser();
        eu1.setAmount(2000);
        eu1.setUser(user1);
        eu1.setExpenseUserType(ExpenseUserType.PAID);
        eu1.setExpense(ex1);

        expenseUserRepositories.save(eu1);

        ExpenseUser eu2 = new ExpenseUser();
        eu2.setAmount(500);
        eu2.setUser(user1);
        eu2.setExpenseUserType(ExpenseUserType.HAD_TO_PAY);
        eu2.setExpense(ex1);

        expenseUserRepositories.save(eu2);

        ExpenseUser eu3 = new ExpenseUser();
        eu3.setAmount(500);
        eu3.setUser(user2);
        eu3.setExpenseUserType(ExpenseUserType.HAD_TO_PAY);
        eu3.setExpense(ex1);

        expenseUserRepositories.save(eu3);

        ExpenseUser eu4 = new ExpenseUser();
        eu4.setAmount(500);
        eu4.setUser(user3);
        eu4.setExpenseUserType(ExpenseUserType.HAD_TO_PAY);
        eu4.setExpense(ex1);

        expenseUserRepositories.save(eu4);

        ExpenseUser eu5 = new ExpenseUser();
        eu5.setAmount(500);
        eu5.setUser(user4);
        eu5.setExpenseUserType(ExpenseUserType.HAD_TO_PAY);
        eu5.setExpense(ex1);

        expenseUserRepositories.save(eu5);

        expenseUsers.add(eu1);
        expenseUsers.add(eu2);
        expenseUsers.add(eu3);
        expenseUsers.add(eu4);
        expenseUsers.add(eu5);


        ex1.setExpenseUsers(expenseUsers);

        expenseRepositories.save(ex1);

        expenses.add(ex1);

        group.setExpenses(expenses);

        groupRepositories.save(group);
        System.out.println("Data generation done");
    }

}
