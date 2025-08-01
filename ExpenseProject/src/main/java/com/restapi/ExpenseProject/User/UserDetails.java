package com.restapi.ExpenseProject.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserDetails {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    List<User_Expenses> arr= new ArrayList<>();

    public UserDetails() {
    }

    public UserDetails(String username, ArrayList<User_Expenses> arr) {
        this.username = username;
        this.arr = arr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<User_Expenses> getArr() {
        return arr;
    }

    public void setArr(List<User_Expenses> arr) {
        this.arr = arr;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", arr=" + arr +
                '}';
    }
}
