package com.restapi.ExpenseProject.controller;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id ) {
super(id);
    }
}
