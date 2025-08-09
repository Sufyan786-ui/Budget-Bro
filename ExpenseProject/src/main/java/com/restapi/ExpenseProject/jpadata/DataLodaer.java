package com.restapi.ExpenseProject.jpadata;

import com.restapi.ExpenseProject.Repository.User_repository;
import com.restapi.ExpenseProject.User.UserDetails;
import com.restapi.ExpenseProject.User.User_Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataLodaer implements CommandLineRunner {
    @Autowired
    private User_repository repo;

    @Override
    public void run(String... args) throws Exception {
        UserDetails user = new UserDetails(); // Or `User user = new User();`
        user.setUsername("Sufyan");
        User_Expenses expense1 = new User_Expenses(5000, LocalDate.now(),
                3000, "Groceries", user);
        User_Expenses expense2 = new User_Expenses(5000, LocalDate.now(),
                2000, "Electricity", user);
        user.setArr(Arrays.asList(expense1, expense2));
        repo.save(user);
    }

    public void addDetails(UserDetails user) {
        List<User_Expenses> arr = user.getArr();
        User_Expenses expense = new User_Expenses(7000, LocalDate.now(),
                2000, "Books", user);
        if (user.getArr() == null) {
            user.setArr(new ArrayList<>());
        }
        user.getArr().add(expense);

        repo.save(user);
    }

    //mthod to get all users
    public List<UserDetails> getAllUsers() {
        return repo.findAll();

    }
}