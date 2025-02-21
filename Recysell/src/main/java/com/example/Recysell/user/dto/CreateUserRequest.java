package com.example.Recysell.user.dto;

public record CreateUserRequest (
        String username,
        String email,
        String password,
        String verifyPassword
){
}
