package edu.utn.utnphones.controller;



import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User login(String dni, String password) throws UserNotFoundException, ValidationException {
        if ((dni != null) && (password != null)) {
            return userService.login(dni, password);
        } else {
            throw new ValidationException("username and password must have a value");
        }
    }


    public User getUserById(Integer userId) {
        return userService.getUser(userId);
    }


    public User createUser(User user) throws UserAlreadyExistsException {
        return userService.createUser(user);
    }

    public void removeUser(User user) throws UserNotFoundException {
        userService.removeUser(user);
    }

    public void removeUsers(List<User> userList) throws UserNotFoundException {
        for (User u : userList) {
            userService.removeUser(u);
        }
    }


    public void updateUser(User user) throws UserNotFoundException {
        userService.updateUser(user);
    }


}
