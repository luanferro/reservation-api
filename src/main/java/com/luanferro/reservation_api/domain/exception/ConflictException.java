package com.luanferro.reservation_api.domain.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String messsage) {super(messsage);}
}
