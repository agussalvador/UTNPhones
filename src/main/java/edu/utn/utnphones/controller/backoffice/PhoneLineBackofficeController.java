package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.PhoneLineController;
import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.PhoneLineRequestDto;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.projection.PhoneLineView;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/backoffice/phone-lines")
public class PhoneLineBackofficeController {

    private final PhoneLineController phoneLineController;

    private final SessionManager sessionManager;

    @Autowired
    public PhoneLineBackofficeController(PhoneLineController phoneLineController, SessionManager sessionManager) {
        this.phoneLineController = phoneLineController;
        this.sessionManager = sessionManager;
    }

    @PostMapping
    public ResponseEntity newPhoneLine(@RequestHeader("Authorization") String sessionToken, @RequestBody PhoneLineRequestDto newPhoneLine) {

        try{
            PhoneLine phoneLine = phoneLineController.addPhoneLine(newPhoneLine);
            return ResponseEntity.created(getLocation(phoneLine)).build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PhoneLineView>> getPhoneLinesByDni(@RequestHeader("Authorization") String sessionToken, @RequestParam(value = "dni", required = false) String dni){

        try{
            List<PhoneLineView> phoneLines = phoneLineController.getPhoneLinesByUserDni(dni);
            return (phoneLines.size() > 0) ? ResponseEntity.ok(phoneLines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    private URI getLocation(PhoneLine phoneLine) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(phoneLine.getPhoneLineId())
                .toUri();
    }



}
