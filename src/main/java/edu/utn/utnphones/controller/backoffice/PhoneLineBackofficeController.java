package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.PhoneLineController;
import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.PhoneLineRequestDto;
import edu.utn.utnphones.exceptions.PhoneLineAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/backoffice")
public class PhoneLineBackofficeController {

    private final PhoneLineController phoneLineController;

    private final SessionManager sessionManager;

    @Autowired
    public PhoneLineBackofficeController(PhoneLineController phoneLineController, SessionManager sessionManager) {
        this.phoneLineController = phoneLineController;
        this.sessionManager = sessionManager;
    }

    @PostMapping
    public ResponseEntity newPhoneLine(@RequestHeader("Authorization") String sessionToken, @RequestBody PhoneLineRequestDto phoneLineDto) throws PhoneLineAlreadyExistsException, UserNotFoundException {

        User currentUser = getCurrentUser(sessionToken);

        return ResponseEntity.ok().build();

    }



    private User getCurrentUser(String sessionToken) throws UserNotFoundException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotFoundException::new);
    }

    private URI getLocation(PhoneLine phoneLine) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(phoneLine.getPhoneLineId())
                .toUri();
    }



}
