package edu.nbu.projectshop.exceptions;

public class ExpiredProductException extends RuntimeException{
    public ExpiredProductException(String message){
        super(message);
    }
}
