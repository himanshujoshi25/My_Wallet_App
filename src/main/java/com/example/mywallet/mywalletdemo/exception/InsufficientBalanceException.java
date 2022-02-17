package com.example.mywallet.mywalletdemo.exception;


public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message){
        super(message);
    }
}