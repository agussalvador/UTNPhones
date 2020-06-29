package edu.utn.utnphones.service;


import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.ClientView;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.PhoneLineDao;
import edu.utn.utnphones.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import javax.jnlp.IntegrationService;
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

    public User addClient (ClientRequestDto newClient) throws UserAlreadyExistsException, ValidationException, CityNotFoundException {

        User user = userDao.findByDni(newClient.getDni()).orElse(null);

        Long idCity = newClient.getCityId();
        String dni = newClient.getDni();
        String firstname = newClient.getFirstname();
        String lastname = newClient.getLastname();

        if(user != null){

            throw new UserAlreadyExistsException("User Already Exist");

        }else if( (idCity!=null )&&( !dni.isEmpty() && dni!=null )&&(!firstname.isEmpty()&&firstname!=null ) && ( !lastname.isEmpty()&&lastname!=null ) ){

            cityDao.findById(newClient.getCityId()).orElseThrow(()->new CityNotFoundException());
            Long idUser = userDao.addClient(newClient.getCityId(), newClient.getFirstname(), newClient.getLastname(), newClient.getDni(), newClient.getTypeLine().name());
            user = userDao.findById(idUser).orElse(null);
            return user;

        }else{

            throw new ValidationException("Error - does not include all necessary information ");
        }

    }



    public User getClientByDni(String dni) throws UserNotFoundException  {
        return  userDao.findByDni(dni).orElseThrow(() -> new UserNotFoundException());
    }

    public List<User> getAllClients(){
        return userDao.findAllClients();
    }


    public void updateClient(String dni, User newClient) throws UserNotFoundException {

        City city = cityDao.findById(newClient.getCity().getCityId()).orElse(null);
        User existingUser = userDao.findByDni(dni).orElseThrow(() -> new UserNotFoundException());

        existingUser.setFirstname((newClient.getFirstname() != null) ? newClient.getFirstname() : existingUser.getFirstname() );
        existingUser.setLastname( (newClient.getLastname() != null) ? newClient.getLastname() : existingUser.getLastname() );
        existingUser.setPassword( (newClient.getPassword() != null) ? newClient.getPassword() : existingUser.getPassword() );

        userDao.save(existingUser);
    }

    public void deleteClient(String dni) throws UserNotFoundException {
        userDao.delete( userDao.findByDni(dni).orElseThrow(() -> new UserNotFoundException()));
    }


}

