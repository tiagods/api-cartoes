package com.tiagods.cartoes.exception;

public class LimiteNaoDisponivelException extends RuntimeException{
    public LimiteNaoDisponivelException(String message){
        super(message);
    }
}
