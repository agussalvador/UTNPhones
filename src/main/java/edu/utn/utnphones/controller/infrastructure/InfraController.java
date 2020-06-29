package edu.utn.utnphones.controller.infrastructure;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.dto.CallRequestDto;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;

@RestController
@RequestMapping("/api/infra")
public class InfraController {

    private final CallController callController;

    @Autowired
    public InfraController(CallController callController) {
        this.callController = callController;
    }

    @PostMapping
    public ResponseEntity<Call> addCall(@RequestBody CallRequestDto callDto) throws ValidationException, ParseException {

        Call call = callController.addCall(callDto);
        return ResponseEntity.created(getLocation(call)).build();
    }

    private URI getLocation(Call call) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(call.getCallId())
                .toUri();
    }

}
