package com.restapi.recipe.exeption;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message){
        super(message);
    }
}
