package edu.utn.utnphones.service;


import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final CityDao cityDao;
    private final UserDao userDao;

    @Autowired
    public UserService(CityDao cityDao, UserDao userDao) {
        this.cityDao = cityDao;
        this.userDao = userDao;
    }

    public User login(String dni, String password) throws UserNotFoundException {
        User user = userDao.getUserByDniAndPwd(dni, password);
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotFoundException());
    }

    private User saveClient (ClientRequestDto newClient) {
        Long idUser = userDao.addClient(newClient.getCityId(), newClient.getFirstname(), newClient.getLastname(), newClient.getDni(), newClient.getTypeLine().name());
        return userDao.findById(idUser).orElse(null);
    }

    public User addClient (ClientRequestDto newClient) throws UserAlreadyExistsException, ValidationException, CityNotFoundException {

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






    /*
    public void updateClient(String dni, User newClient){

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

*/

}

