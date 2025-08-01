package com.restapi.ExpenseProject.Repository;

import com.restapi.ExpenseProject.User.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_repository extends JpaRepository<UserDetails,Integer> {
    //List<UserDetails> findByUsername(int id);
}
