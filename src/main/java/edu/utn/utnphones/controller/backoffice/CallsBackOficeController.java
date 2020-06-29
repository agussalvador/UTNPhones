package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.CallView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<CallView>> getCallsByDni(@RequestParam(value = "dni_client") String dni) throws ValidationException, UserNotFoundException {

        List<CallView> calls = callController.getCallsByDni(dni);
        return (calls.size() != 0) ? ResponseEntity.ok(calls) : ResponseEntity.noContent().build();
    }

    /*Parcial - Laborarotio V -  01-06-2020  */
    @GetMapping("/last-calls")
    public ResponseEntity<List<CallView>> getLast3CallsByDni(@RequestParam(value = "dni") String dni) throws UserNotFoundException, ValidationException {

        List<CallView> calls = callController.getLast3CallsByDni(dni);
        return (calls.size() != 0) ? ResponseEntity.ok(calls) : ResponseEntity.noContent().build();
    }

}
