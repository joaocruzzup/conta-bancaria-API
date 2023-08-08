package com.catalisa.contaBancaria.exception;

public class ContaNaoEncontradaException extends RuntimeException{
    public ContaNaoEncontradaException(String message) {
        super(message);
    }
}
