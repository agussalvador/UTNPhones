package edu.utn.utnphones.exceptions;

public class InvalidLoginException extends Exception {

    public InvalidLoginException(String message) {
        super(message);
    }

    public InvalidLoginException(Throwable cause) {
        super(cause);
    }


}
