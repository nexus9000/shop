package edu.nbu.projectshop.exceptions;

public class GoodsMissingException extends RuntimeException{
    public GoodsMissingException(String message){
        super(message);
    }
}
