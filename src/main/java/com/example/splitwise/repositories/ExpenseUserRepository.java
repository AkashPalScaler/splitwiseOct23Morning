package com.example.splitwise.repositories;

import com.example.splitwise.models.ExpenseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
}
