package edu.utn.utnphones.service;


import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserDao dao;


    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public User login(String dni, String password) throws UserNotFoundException {
        User user = dao.getByDni(dni, password);
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotFoundException());
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        return dao.save(user);
    }


    public void removeUser(User user) throws UserNotFoundException {
        dao.delete(user);
    }

    public User updateUser(User user) throws UserNotFoundException {
     return dao.save(user);
    }

    public User getUser(Integer userId){
        return dao.getOne(userId);
    }

}
