package edu.utn.utnphones.service;


import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.domain.enums.Role;
import edu.utn.utnphones.domain.enums.TypeLine;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    UserService userService;
    City city;
    User user;
    List<User> userList;
    ClientRequestDto clientRequestDto;

    @Mock
    private UserDao userDao;
    @Mock
    private CityDao cityDao;

    @Before
    public void setUp() {
        initMocks(this);
        userService = new UserService(userDao, cityDao);

        clientRequestDto = new ClientRequestDto((long) 1, "Juan", "Perez", "32165498","321asd", TypeLine.home);

        city = new City((long)1,"Mar del plata","0223",null);
        user = new User((long) 1, "32165498", "Joaquin", "Lopez", "pwd123", true, Role.client, city);
        userList = new ArrayList<>();
        userList.add(user);
    }

/*
    @Test
    public void addClientOk() throws UserAlreadyExistsException, CityNotFoundException, ValidationException, NoSuchAlgorithmException {

        when(userDao.findByDni(clientRequestDto.getDni()) ).thenReturn(null);
        when(cityDao.findById(clientRequestDto.getCityId())).thenReturn(Optional.ofNullable(city));

        when(userDao.addClient(clientRequestDto.getCityId(), clientRequestDto.getFirstname(), clientRequestDto.getLastname(), clientRequestDto.getDni(), clientRequestDto.getPassword(), clientRequestDto.getTypeLine().name() )).thenReturn((long)2);

        User createUser = new User((long) 2, "32165498", "Juan", "Perez", "pwd123", true, Role.client, city);
        when(userDao.getOne((long)2)).thenReturn(createUser);

        User returnedUser = userService.addClient(clientRequestDto);

        assertEquals(createUser, returnedUser);
    }*/

    @Test(expected = UserAlreadyExistsException.class)
    public void addClientAlreadyExist() throws NoSuchAlgorithmException, UserAlreadyExistsException, CityNotFoundException {

        clientRequestDto = new ClientRequestDto((long) 1, "Agustin", "Salvador", "4333455","321asd", TypeLine.home);
        when(userDao.findByDni(clientRequestDto.getDni())).thenThrow(UserAlreadyExistsException.class);
        userService.addClient(clientRequestDto);
    }



//
//    @Test
//    void login() {
//    }
//
///*
/*
    @Test
    void addClientCityNotFound() {
        ClientRequestDto clientRequestDto = new ClientRequestDto((long) 1, "Santiago", "labatut", "4333455", "321asd", TypeLine.home);
        when(userDao.findByDni(clientRequestDto.getDni())).thenReturn(null);
        when(cityDao.findById(clientRequestDto.getCityId())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> userService.addClient(clientRequestDto));
    }
    */
    @Test
    public void addClientValidationException(){
        /*ClientRequestDto clientRequestDto = new ClientRequestDto(null, "", "", "4333455", TypeLine.home);
        when(userDao.findByDni(clientRequestDto.getDni())).thenReturn(null);
        //when(cityDao.findById(clientRequestDto.getCityId())).thenReturn();
        assertThrows(ValidationException.class, () -> userService.addClient(clientRequestDto));*/
    }
    @Test
    public void getClientByDniOk() throws UserNotFoundException {
        when(userDao.findByDni("4333444")).thenReturn(java.util.Optional.ofNullable(user));
        User user2 = userService.getClientByDni("4333444");
        assertEquals(user2,user);
    }
    @Test
    public void getClientByDniUserNotFound() throws UserNotFoundException {
       /* when(userDao.findByDni("4333444")).thenReturn(null);
//        User user2 = userService.getClientByDni("4333444");
//        assertEquals(user2,user);
        assertThrows(UserNotFoundException.class, () -> userService.getClientByDni("4333444"));*/
    }

    @Test
    public void getAllClientsOk() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userDao.findAllClients()).thenReturn(users);
        List<User> users2 = userDao.findAllClients();
        assertEquals(users.get(0),users2.get(0));
        assertEquals(users.size(),users2.size());
    }

    @Test
    public void getAllClientsEmpty(){
        List<User> users = new ArrayList<>();
        when(userDao.findAllClients()).thenReturn(users);
        List<User> users2 = userDao.findAllClients();
        assertEquals(users.isEmpty(),users2.isEmpty());
        assertEquals(users.size(),users2.size());
    }

//    @Test
//    public void updateClient() throws UserNotFoundException {
//
//        when(cityDao.findById(user.getCity().getCityId())).thenReturn(java.util.Optional.ofNullable(city));
//        when(userDao.findByDni(user.getDni())).thenReturn(java.util.Optional.ofNullable(user));
//        doNothing().when(userDao).save(user);
//        userService.updateClient("123",user);
//        verify(userDao, times(1)).save(user);
//    }


    @Test
    public void deleteClientOk() {
        /*when(userDao.findByDni(clientRequestDto.getDni())).thenReturn(java.util.Optional.ofNullable(user));
        doNothing().when(userDao).delete(user);*/

    }

}