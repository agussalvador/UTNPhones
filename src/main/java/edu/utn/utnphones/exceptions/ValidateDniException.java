package edu.utn.utnphones.exceptions;

public class ValidateDniException extends Exception{

    public String getMessage() {
        return "Invalid dni";
    }
}
