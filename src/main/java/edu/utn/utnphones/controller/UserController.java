package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User login(String dni, String password) throws UserNotFoundException, ValidationException {
        if ((dni!=null) && (password!=null)) {
            return userService.login(dni, password);
        } else {
            throw new ValidationException("dni and password must have a value");
        }
    }

    public User addClient (ClientRequestDto newClient) throws UserAlreadyExistsException, JpaSystemException, ValidationException, CityNotFoundException {
        return userService.addClient(newClient);
    }

    public User getClientByDni(String dni) throws UserNotFoundException, ValidationException {
        if ((dni != null) &&(!dni.isEmpty()) ) {
            return userService.getClientByDni(dni);
        } else {
            throw new ValidationException("dni must have a value");
        }
    }

    public List<User> getAllClients()throws JpaSystemException{
        return userService.getAllClients();
    }


    public void updateClient(String dni, User newClient) throws JpaSystemException, UserNotFoundException {
        userService.updateClient(dni, newClient);
    }

    public void deleteClient(String  dni) throws JpaSystemException, UserNotFoundException {
        userService.deleteClient(dni);
    }

}
