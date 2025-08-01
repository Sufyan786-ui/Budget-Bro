package com.restapi.ExpenseProject.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class User_Expenses {
    @Id
    @GeneratedValue
    private int id;
    private int amount;
    private int expense;
    private LocalDate date;
    private String reason;
    @ManyToOne
    @JsonBackReference
    private UserDetails user;

    public User_Expenses() {
    }

    public User_Expenses(int amount, LocalDate date, int expense, String reason, UserDetails user) {
        this.amount = amount;
        this.date = date;
        this.expense = expense;
        this.reason = reason;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "User_Expenses{" +
                "id=" + id +
                ", amount=" + amount +
                ", expense=" + expense +
                ", date=" + date +
                ", reason='" + reason + '\'' +
                ", user=" + user +
                '}';
    }
}
