package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.CallController;
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
    private SessionManager sessionManager;

    @Autowired
    public CallsBackOficeController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @GetMapping
    public ResponseEntity<List<CallView>> getCallsByDni(@RequestHeader("Authorization") String sessionToken, @RequestParam(value = "dni") String dni) throws ValidationException {
        try{
            List<CallView> calls = callController.getCallsByDni(dni);
            return (calls.size() > 0) ? ResponseEntity.ok(calls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
