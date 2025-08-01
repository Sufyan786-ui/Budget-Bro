package com.restapi.ExpenseProject.openai;

import com.restapi.ExpenseProject.Repository.User_repository;
import com.restapi.ExpenseProject.User.UserDetails;
import com.restapi.ExpenseProject.User.User_Expenses;
import com.restapi.ExpenseProject.controller.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ai")
public class Openai_Controller {
    @Autowired
    private User_repository repo;

    @Autowired
private Service service;
    @GetMapping("/ask/{id}")
    public String askAI(@RequestParam String prompt,@PathVariable int id) throws Exception {
        Optional<UserDetails> user= repo.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User is not found"+id);
        }
        List<User_Expenses> expense= user.get().getArr();
        StringBuilder sb= new StringBuilder("THis are my Expenses\n");
        int totalexpense=0;
int budget=0;
        for (User_Expenses e : expense){
            budget= e.getAmount();
            sb.append("- ").append(e.getReason())
                    .append(": ₹").append(e.getExpense())
                    .append(" (").append(e.getDate()).append(")\n");
            totalexpense += e.getExpense();
        }

        sb.append("Total spent: ₹").append(totalexpense).append("\n\n");
        sb.append("Total Budget: ₹").append(budget).append("\n\n");
       sb.append("Now answer this question from the user:\n")
                .append(prompt);
        return service.callgpt(sb.toString());
    }
}
