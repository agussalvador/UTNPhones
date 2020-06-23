package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





    /*
    * 	- llamadas realizadas x el cliente (by Dni)
	*		GET: /api/backoffice/calls?dni='1234567'
    * */






}
