package com.tiagods.cartoes.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(String message){
        super(message);
    }
}
