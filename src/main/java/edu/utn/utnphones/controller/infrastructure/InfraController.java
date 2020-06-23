package edu.utn.utnphones.controller.infrastructure;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.dto.CallRequestDto;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/infra/")
public class InfraController {

    private final CallController callController;
    private SessionManager sessionManager;

    @Autowired
    public InfraController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @PostMapping
    public ResponseEntity addCall(@RequestBody CallRequestDto callDto){

        // Call call = callController.add();

        Call call = null;

        return ResponseEntity.created(getLocation(call)).build();


    }


    /*
    * 	- Ingresar una llamada
		POST: /api/infra
		{
			"numberOrigin":"0223-5677456",
			"numberDestination":"2267-1231233",
			"duration":45,
			"date":"01/06/2020"
		}
    * */




    private URI getLocation(Call call) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(call.getCallId())
                .toUri();
    }

}
