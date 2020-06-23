package edu.utn.utnphones.controller.webApp;

import edu.utn.utnphones.controller.BillController;
import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/web/")
public class UserWebController {

    private final CallController callController;
    private final BillController billController;
    private final SessionManager sessionManager;

    @Autowired
    public UserWebController(CallController callController, BillController billController, SessionManager sessionManager) {
        this.callController = callController;
        this.billController = billController;
        this.sessionManager = sessionManager;
    }



		/* todo lamadas realizadas x el usuario logueado, por rango de fechas.
		    GET: /api/web/calls?from='12/05/2020'&to='12/06/2020' */





        /* todo facturas x el usuario logueado, por rango de fechas.
            GET: /api/web/bills?from='12/05/2020'&to='12/06/2020' */






        /*todo  TOP 10 Destinos (ciudades o phoneLines) mas Llamados por el usuario logueado.
             GET: /api/web/cities*/





    private User getCurrentUser(String sessionToken) throws UserNotFoundException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotFoundException::new);
    }








}
