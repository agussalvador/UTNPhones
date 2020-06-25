package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.PhoneLineController;
import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.PhoneLineRequestDto;
import edu.utn.utnphones.exceptions.PhoneLineNotFoundException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
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

    @Autowired
    public PhoneLineBackofficeController(PhoneLineController phoneLineController) {
        this.phoneLineController = phoneLineController;
    }

    @PostMapping
    public ResponseEntity newPhoneLine(@RequestBody PhoneLineRequestDto newPhoneLine) throws ValidationException, UserNotFoundException {

        PhoneLine phoneLine = phoneLineController.addPhoneLine(newPhoneLine);
        return ResponseEntity.created(getLocation(phoneLine)).build();
    }

    @GetMapping("/client")
    public ResponseEntity<List<PhoneLineView>> getPhoneLinesByDni(@RequestParam(value = "dni") String dni) throws ValidationException, UserNotFoundException {

        List<PhoneLineView> phoneLines = phoneLineController.getPhoneLinesByUserDni(dni);
        return (phoneLines.size() != 0) ? ResponseEntity.ok(phoneLines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("")
    public ResponseEntity<List<PhoneLineView>> getPhoneLines(){

        List<PhoneLineView> phoneLines = phoneLineController.getPhoneLines();
        return (phoneLines.size() > 0) ? ResponseEntity.ok(phoneLines): ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhoneLine>suspendPhone(@PathVariable("id")Long idPhoneLine) throws PhoneLineNotFoundException {

        PhoneLine phoneLine = phoneLineController.deletePhoneLine(idPhoneLine);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneLine>updatePhoneLine(@PathVariable("id")Long idPhoneLine) throws PhoneLineNotFoundException {

        PhoneLine phoneLine = phoneLineController.updatePhoneLine(idPhoneLine);
        return ResponseEntity.accepted().build();
    }

    private URI getLocation(PhoneLine phoneLine) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(phoneLine.getPhoneLineId())
                .toUri();
    }

}
