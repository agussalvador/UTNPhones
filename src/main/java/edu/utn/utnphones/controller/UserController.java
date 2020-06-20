package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
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
        if ((dni != null) && (password != null)) {
            return userService.login(dni, password);
        } else {
            throw new ValidationException("dni and password must have a value");
        }
    }

    public User addClient (ClientRequestDto newClient) throws UserAlreadyExistsException, JpaSystemException {
        return userService.addClient(newClient);
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    public User getUserByDni(String dni) throws UserNotFoundException {
        if(dni!=null){
            return userService.getUserByDni(dni);
        }else{
            throw new UserNotFoundException();
        }
    }

    public List<User> getClients()throws JpaSystemException{
        return userService.getClients();
    }

    public void updateClient(String dni, User newClient)throws JpaSystemException{
        userService.updateClient(dni, newClient);
    }

    public void deleteClient(String  dni)throws JpaSystemException{
        userService.deleteClient(dni);
    }

}
