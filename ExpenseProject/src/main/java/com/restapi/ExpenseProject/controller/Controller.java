package com.restapi.ExpenseProject.controller;

import com.restapi.ExpenseProject.Repository.User_repository;
import com.restapi.ExpenseProject.User.UserDetails;
import com.restapi.ExpenseProject.User.User_Expenses;
import com.restapi.ExpenseProject.jpadata.DataLodaer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
   @Autowired
    private DataLodaer load;
   @Autowired
   private User_repository repo;
    @GetMapping("/users")
    public List<UserDetails> getalluser(){
        List<UserDetails> list = repo.findAll();
        return list;
    }
    @GetMapping("/users/{id}")
    public UserDetails userbyid(@PathVariable int id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("List is empty for id " + id));
    }

    @PostMapping("/users")
    public ResponseEntity<Object> add_newuser(@Valid @RequestBody UserDetails user){
        UserDetails save=  repo.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable int id){
        repo.deleteById(id);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDetails> update(@PathVariable int id, @RequestBody UserDetails user) {
        Optional<UserDetails> list = repo.findById(id);
        if (list.isEmpty()) {
            throw new UserNotFoundException("List is empty for id " + id);
        }

        UserDetails u = list.get();
        u.setUsername(user.getUsername());

        // Clear old expenses (orphanRemoval will delete them)
        u.getArr().clear();

        // Add new expenses and set the parent user
        for (User_Expenses expense : user.getArr()) {
            expense.setUser(u);
            u.getArr().add(expense);
        }

        // ❌ DO NOT do this: u.setArr(user.getArr());

        repo.save(u);
        return ResponseEntity.ok(u);
    }
    //.......Expense Operations............//
    @GetMapping("/users/{id}/exp")
    public ResponseEntity<List<User_Expenses>> expense(@PathVariable int id){
        Optional<UserDetails> user= repo.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User is not found"+id);
        }
        return ResponseEntity.ok(user.get().getArr());
    }
    @PostMapping("/users/{id}/exp")
    public ResponseEntity<User_Expenses> expense(@PathVariable int id, @RequestBody User_Expenses user) {
        Optional<UserDetails> userDetails = repo.findById(id);
        if (userDetails.isEmpty()) {
            throw new UserNotFoundException("User is not found " + id);
        }

        UserDetails expense = userDetails.get();
        user.setUser(expense); // set the user inside the expense (VERY IMPORTANT)
        expense.getArr().add(user);

        repo.save(expense); // Will save user + expense due to CascadeType.ALL

        return ResponseEntity.status(HttpStatus.CREATED).body(user); // ✅ return the expense object
    }
    @PutMapping("/users/{id}/exp/{userid}")
    public ResponseEntity<User_Expenses> updte(@PathVariable int id, @PathVariable int userid,
                                               @RequestBody User_Expenses expense) {
        Optional<UserDetails> userDetails = repo.findById(id);
        if (userDetails.isEmpty()) {
            throw new UserNotFoundException("User is not found " + id);
        }

        UserDetails u = userDetails.get();
        List<User_Expenses> list = u.getArr();
        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == userid) {
                expense.setUser(u);  // Set parent user
                list.set(i, expense); // Update the expense
                found = true;
                break;
            }
        }

        if (!found) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Expense ID not found
        }

        u.setArr(list);
        repo.save(u); // Save updated user (Cascade saves expenses)

        return ResponseEntity.ok(expense);
    }
    @DeleteMapping("/users/{id}/exp/{userid}")
    public void deleteexp(@PathVariable int id,@PathVariable int userid){
        Optional<UserDetails> user= repo.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User is not found " + id);
        }
        UserDetails userDetails= user.get();
        List<User_Expenses> list= userDetails.getArr();
        int flag=0;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId()==userid){
list.remove(i);
break;
            }
        }
repo.save(userDetails);
    }


}

