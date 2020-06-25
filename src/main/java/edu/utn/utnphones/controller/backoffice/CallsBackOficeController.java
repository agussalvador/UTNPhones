package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.CallView;
import edu.utn.utnphones.projection.ClientView;
import edu.utn.utnphones.projection.PhoneLineView;
import edu.utn.utnphones.session.SessionManager;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backoffice/calls")
public class CallsBackOficeController {

    private final CallController callController;

    @Autowired
    public CallsBackOficeController(CallController callController) {
        this.callController = callController;
    }

    @GetMapping
    public ResponseEntity<List<CallView>> getCallsByDni(@RequestParam(value = "dni") String dni) throws ValidationException, UserNotFoundException {

        List<CallView> calls = callController.getCallsByDni(dni);
        return (calls.size() != 0) ? ResponseEntity.ok(calls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*Parcial - Laborarotio V -  01-06-2020  */
    @GetMapping("/last-calls")
    public ResponseEntity<List<CallView>> getLast3CallsByDni(@RequestParam(value = "dni") String dni) throws UserNotFoundException, ValidationException {

        List<CallView> calls = callController.getLast3CallsByDni(dni);
        return (calls.size() != 0) ? ResponseEntity.ok(calls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
