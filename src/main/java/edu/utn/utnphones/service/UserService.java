package edu.utn.utnphones.service;


import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.projection.ClientView;
import edu.utn.utnphones.projection.PhoneLineView;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.PhoneLineDao;
import edu.utn.utnphones.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final CityDao cityDao;
    private final UserDao userDao;
    private final PhoneLineDao phoneLineDao;

    @Autowired
    public UserService(CityDao cityDao, UserDao userDao, PhoneLineDao phoneLineDao) {
        this.cityDao = cityDao;
        this.userDao = userDao;
        this.phoneLineDao = phoneLineDao;
    }

    public User login(String dni, String password) throws UserNotFoundException {
        User user = userDao.getUserByDni(dni, password);
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotFoundException());
    }

    public User addClient (ClientRequestDto newClient) throws JpaSystemException, UserAlreadyExistsException {

        Optional<User> user = userDao.findByDni(newClient.getDni());
        if(user.get() != null) {
            throw new UserAlreadyExistsException("User Already Exist");
        }
        Long idUser = userDao.addClient(newClient.getCityId(), newClient.getFirstname(), newClient.getLastname(), newClient.getDni(), newClient.getPassword(), newClient.getTypeLine().name());
        user = userDao.findById(idUser);
        return user.orElse(null);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userDao.findById(id).orElse(null);
    }

    public User getClientByDni(String dni) throws JpaSystemException, UserNotFoundException {
        User client = userDao.getUserByDni(dni);
        return Optional.ofNullable(client).orElseThrow(() -> new UserNotFoundException());
    }

    public List<ClientView> getAllClients()throws JpaSystemException{
        return userDao.getAllClients();
    }

    public void updateClient(String dni, User newClient)throws JpaSystemException{

        City city = cityDao.findById(newClient.getCity().getCityId()).orElse(null);
        User existingUser = userDao.getUserByDni(dni);

        existingUser.setFirstname((newClient.getFirstname() != null) ? newClient.getFirstname() : existingUser.getFirstname() );
        existingUser.setLastname( (newClient.getLastname() != null) ? newClient.getLastname() : existingUser.getLastname() );
        existingUser.setPassword( (newClient.getPassword() != null) ? newClient.getPassword() : existingUser.getPassword() );

        userDao.save(existingUser);
    }

    public void deleteClient(String dni){
        userDao.delete( userDao.getUserByDni(dni));
    }

}

