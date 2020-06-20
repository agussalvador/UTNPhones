package edu.utn.utnphones.exceptions;

public class PhoneLineAlreadyExistsException extends RecordExistsException {

    public PhoneLineAlreadyExistsException(String message) {
        super(message);
    }

    public String getMessage() {
        return "Phone Line Already Exist!";
    }
}
