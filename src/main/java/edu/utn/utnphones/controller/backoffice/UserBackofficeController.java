package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.controller.UserController;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
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
public class UserBackofficeController {

    private final UserController userController;
    private final SessionManager sessionManager;

    @Autowired
    public UserBackofficeController(UserController userController, SessionManager sessionManager) {
        this.userController = userController;
        this.sessionManager = sessionManager;
    }

    @PostMapping
    public ResponseEntity<User> addNewClient(@RequestHeader("Authorization") String sessionToken, @RequestBody ClientRequestDto newClient) {

        try {
            User user = userController.addClient(newClient);
            return ResponseEntity.created(getLocation(user)).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<User> getClientByDni(@RequestHeader("Authorization") String sessionToken, @RequestParam(value = "dni", required = false) String dni) throws ValidationException {

        ResponseEntity<User> responseEntity;
        try {
            User user = userController.getUserByDni(dni);
            return responseEntity = ResponseEntity.ok(user);
        } catch (UserNotFoundException e ) {
            return responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String sessionToken){

        List<User> users = new ArrayList<>();
        try{
            users = userController.getClients();
            return (users.size() > 0) ? ResponseEntity.ok(users) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

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


    /*
    private User getCurrentUser(String sessionToken) throws UserNotFoundException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotFoundException::new);
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
