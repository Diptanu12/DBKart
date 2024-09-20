package com.example.DbKart.exception;

public class ProductNotAvailableException extends RuntimeException{

    public ProductNotAvailableException(String message) {
        super(message);
    }
}