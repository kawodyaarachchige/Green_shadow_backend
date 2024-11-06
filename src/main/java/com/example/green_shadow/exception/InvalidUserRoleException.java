package com.example.green_shadow.exception;

public class InvalidUserRoleException extends RuntimeException{
    public InvalidUserRoleException (String message){
        super(message);
    }
}
