package edu.utn.utnphones.exceptions;

public class TarriffAlreadyExistsException extends RecordExistsException {

    public TarriffAlreadyExistsException(String message) {
        super(message);
    }

}
