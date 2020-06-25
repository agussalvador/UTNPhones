package edu.utn.utnphones.controller.ParcialWebController;

import edu.utn.utnphones.controller.UserController;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.ClientView;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Parcial")
public class ParcialController {

    private final UserController userController;


    @Autowired
    public ParcialController(UserController userController) {
        this.userController = userController;

    }

    @GetMapping("/{dni}")
    public ResponseEntity<ClientView> getClientByDni(@PathVariable String dni) throws ValidationException, UserNotFoundException {

        try {
            ClientView client = userController.getClientByDni(dni);
            return ResponseEntity.ok(client);
        } catch (JpaSystemException e ) {
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ClientView>> getAllUsers(){

        List<ClientView> clients = new ArrayList<>();
        try{
            clients = userController.getAllClients();
            return (clients.size() > 0) ? ResponseEntity.ok(clients) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
