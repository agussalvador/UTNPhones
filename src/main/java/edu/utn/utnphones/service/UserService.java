package edu.utn.utnphones.service;


import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.*;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.UserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;
    private final CityDao cityDao;

    public UserService(UserDao userDao, CityDao cityDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
    }


    private User saveClient (ClientRequestDto newClient) throws NoSuchAlgorithmException {
        Long idUser = userDao.addClient(newClient.getCityId(), newClient.getFirstname(), newClient.getLastname(), newClient.getDni(), this.hashPwd(newClient.getPassword()), newClient.getTypeLine().name());
        return userDao.findById(idUser).orElse(null);
    }

    public User addClient (ClientRequestDto newClient) throws UserAlreadyExistsException, ValidationException, CityNotFoundException, NoSuchAlgorithmException {

        if(!newClient.isValid()) throw new ValidationException("Error - does not include all necessary information ");
        Optional<User> user = userDao.findByDni(newClient.getDni());
        if(user.isPresent()) throw new UserAlreadyExistsException();
        cityDao.findById(newClient.getCityId()).orElseThrow(()->new CityNotFoundException());

        return saveClient(newClient);
    }

    public User getClientByDni(String dni) throws UserNotFoundException  {
        return  userDao.findByDni(dni).orElseThrow(() -> new UserNotFoundException());
    }

    public List<User> getAllClients(){
        return userDao.findAllClients();
    }

    public User login(String dni, String password) throws UserNotFoundException, InvalidLoginException, NoSuchAlgorithmException {

        User user = userDao.getUserByDniAndPwd(dni,  this.hashPwd(password)).orElse(null);
        if( user != null) {
            return Optional.ofNullable(user).orElseThrow(() -> new UserNotFoundException());
        }else{
            throw new InvalidLoginException("dni and/or password is incorrect.");
        }
    }


    private String hashPwd(String pass) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] data = pass.getBytes();
        m.update(data, 0, data.length);
        BigInteger i = new BigInteger(1, m.digest());
        return String.format("%1$032X", i);
    }


    public void updateClient(String dni, ClientRequestDto newClient) throws CityNotFoundException, UserNotFoundException {

        City city = cityDao.findById(newClient.getCityId()).orElseThrow(()-> new CityNotFoundException());

        User existingUser = userDao.findByDni(dni).orElseThrow(() -> new UserNotFoundException());

        existingUser.setCity( city );
        existingUser.setFirstname( (newClient.getFirstname() != null) ? newClient.getFirstname() : existingUser.getFirstname() );
        existingUser.setLastname( (newClient.getLastname() != null) ? newClient.getLastname() : existingUser.getLastname() );
        existingUser.setDni( (newClient.getDni() != null) ? newClient.getDni() : existingUser.getDni() );

        userDao.save(existingUser);
    }

/*
    public void deleteClient(String dni){
        userDao.delete( userDao.getUserByDni(dni));
    }

*/

}

