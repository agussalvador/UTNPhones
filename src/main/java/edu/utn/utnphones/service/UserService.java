package edu.utn.utnphones.service;


import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.Role;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.PhoneLineDao;
import edu.utn.utnphones.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

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

        try{
            User user = new User();
            user.setFirstname(newClient.getFirstname());
            user.setLastname(newClient.getLastname());
            user.setDni(newClient.getDni());
            user.setPassword(newClient.getPassword());
            user.setRole(Role.client);
            user.setEnabled(true);
            user.setCity(cityDao.findById(newClient.getCityId()).get());
            userDao.save(user);

            phoneLineDao.generateNumber(newClient.getDni(), newClient.getTypeLine().name());
            return user;
        }catch (JpaSystemException ex){
            throw new UserAlreadyExistsException("User Already Exist!");
        }
    }

    public User getUserById(Integer id) throws UserNotFoundException {
        return userDao.findById(id).orElse(null);
    }

    public User getUserByDni(String dni) throws UserNotFoundException {

        try {
            User user = userDao.getUserByDni(dni);
            return Optional.ofNullable(user).orElseThrow(() -> new UserNotFoundException());
        } catch (JpaSystemException ex) {
            throw new UserNotFoundException();
        }
    }

    public List<User> getClients()throws JpaSystemException{
        return userDao.getAll();
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

