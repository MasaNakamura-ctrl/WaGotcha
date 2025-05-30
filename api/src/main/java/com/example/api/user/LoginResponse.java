package com.example.api.user;

public class LoginResponse {
    private String message;
    private UserResponse user;

    public LoginResponse(String message, UserResponse user){
        this.message = message;
        this.user = user;
    }

    public String getMessage(){
        return message;
    }

    public UserResponse getUser(){
        return user;
    }

    public static class UserResponse{
        private int userId;
        private String userName;

        public UserResponse(int userId, String userName){
            this.userId = userId;
            this.userName = userName;
        }
        
        public int getUserId(){
            return userId;
        }

        public String getUserName(){
            return userName;
        }
    }
}

