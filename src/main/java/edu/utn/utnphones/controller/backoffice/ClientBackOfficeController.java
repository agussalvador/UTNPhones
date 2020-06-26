package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.UserController;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.ClientView;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/backoffice/clients")
public class ClientBackOfficeController {

    private final UserController userController;

    @Autowired
    public ClientBackOfficeController(UserController userController) {
        this.userController = userController;
    }

    @PostMapping
    public ResponseEntity<User> addNewClient(@RequestBody ClientRequestDto newClient) throws UserAlreadyExistsException, ValidationException, CityNotFoundException {

        User user = userController.addClient(newClient);
        return ResponseEntity.created(getLocation(user)).build();
    }

    @GetMapping
    public ResponseEntity<User> getClientByDni(@RequestParam(value = "dni") String dni) throws ValidationException, UserNotFoundException {

        User client = userController.getClientByDni(dni);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> clients = new ArrayList<>();
        clients = userController.getAllClients();
        return (clients.size() != 0) ? ResponseEntity.ok(clients) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*
    @PutMapping
    public ResponseEntity<User> updateUserByDni(@RequestHeader("Authorization") String sessionToken, @RequestParam(value = "dni", required = false) String dni , @RequestBody User newClient){

        try{
            userController.updateClient(dni, newClient);
            return ResponseEntity.accepted().build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity deleteClient(@RequestHeader("Authorization") String sessionToken, @RequestParam(value = "dni", required = false) String dni){

        try{
            userController.deleteClient(dni);
            return ResponseEntity.ok().build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    private URI getLocation(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUserId())
                .toUri();
    }

}
