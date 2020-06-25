package edu.utn.utnphones.controller.webApp;

import edu.utn.utnphones.controller.BillController;
import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.CallView;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/web")
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



    /* todo facturas x el usuario logueado, por rango de fechas.
            GET: /api/web/bills?from='12/05/2020'&to='12/06/2020' */


    // llamadas realizadas x el usuario logueado, por rango de fechas.
    @GetMapping("/calls")
    public ResponseEntity<List<CallView>> getCalls(@RequestHeader("Authorization") String sessionToken, @RequestParam(value = "from", required = false) String from, @RequestParam(value = "to", required = false) String to) throws ParseException, ValidationException, UserNotFoundException {

        User currentUser = getCurrentUser(sessionToken);
        List<CallView> calls = new ArrayList<>();
        if ((from != null) && (to != null)) {
            Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(from);
            Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(to);
            calls = callController.getCallsByUserFilterByDate(currentUser.getDni(), fromDate, toDate);
        } else {
            calls = callController.getCallsByDni(currentUser.getDni());
        }
        return (calls.size() > 0) ? ResponseEntity.ok(calls) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //  TOP 10 destinos más llamados por el usuario.
    @GetMapping("/calls/cities")
    public ResponseEntity<List<City>> getTOP10MostCalledDestination (@RequestHeader("Authorization") String sessionToken) throws UserNotFoundException, ValidationException {

        List<City> callsDestination = new ArrayList<>();
        try{
            callsDestination = callController.getTOP10MostCalledDestination(getCurrentUser(sessionToken).getDni());
            return (callsDestination.size() > 0) ? ResponseEntity.ok(callsDestination) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private User getCurrentUser(String sessionToken) throws UserNotFoundException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotFoundException::new);
    }


}
