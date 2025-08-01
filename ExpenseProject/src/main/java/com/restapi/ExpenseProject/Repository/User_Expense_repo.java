package com.restapi.ExpenseProject.Repository;

import com.restapi.ExpenseProject.User.User_Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Expense_repo extends JpaRepository<User_Expenses,Integer> {
}
