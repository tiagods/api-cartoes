package com.tiagods.cartoes.exception;

import java.util.function.Supplier;

public class OperationTypeException extends RuntimeException{

    public OperationTypeException(String message) {
        super(message);
    }
}
