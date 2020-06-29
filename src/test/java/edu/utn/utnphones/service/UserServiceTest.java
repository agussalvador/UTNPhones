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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {


    User user;
    UserService userService;
    City city;
    ClientRequestDto clientRequestDto;
    @Mock
    private UserDao userDao;
    @Mock
    private CityDao cityDao;
    @BeforeEach
    void setUp() {
        initMocks(this);
        userService = new UserService(cityDao,userDao);
        city = new City((long)1,"Mar del plata","0223",null);
        user = new User((long) 1, "4333444", "santiago", "labatut", "pwd123", true, Role.client, city);
    }

    @Test
    void login() {
    }

    @Test
    void addClientOk() throws UserAlreadyExistsException, CityNotFoundException, ValidationException {
       /* ClientRequestDto clientRequestDto = new ClientRequestDto((long) 1, "Santiago", "labatut", "4333444", TypeLine.home);
        when(userDao.findByDni(clientRequestDto.getDni())).thenReturn(null);
        when(cityDao.findById(clientRequestDto.getCityId())).thenReturn(java.util.Optional.ofNullable(city));
        when(userDao.addClient(clientRequestDto.getCityId(), clientRequestDto.getFirstname(), clientRequestDto.getLastname(), clientRequestDto.getDni(), clientRequestDto.getTypeLine().name())).thenReturn(user.getUserId());
        when(userDao.findById(user.getUserId())).thenReturn(java.util.Optional.ofNullable(user));
        User user2 = userService.addClient(clientRequestDto);
        assertEquals(user,user2);*/

    }

    @Test
    void addClientAlreadyExist(){
        ClientRequestDto clientRequestDto = new ClientRequestDto((long) 1, "Santiago", "labatut", "4333455", TypeLine.home);
        when(userDao.findByDni(clientRequestDto.getDni())).thenReturn(java.util.Optional.ofNullable(user));
        assertThrows(UserAlreadyExistsException.class, () -> userService.addClient(clientRequestDto));
    }
    @Test
    void addClientCityNotFound() {
        ClientRequestDto clientRequestDto = new ClientRequestDto((long) 1, "Santiago", "labatut", "4333455", TypeLine.home);
        when(userDao.findByDni(clientRequestDto.getDni())).thenReturn(null);
        when(cityDao.findById(clientRequestDto.getCityId())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> userService.addClient(clientRequestDto));
    }
    @Test
    void addClientValidationException(){
        /*ClientRequestDto clientRequestDto = new ClientRequestDto(null, "", "", "4333455", TypeLine.home);
        when(userDao.findByDni(clientRequestDto.getDni())).thenReturn(null);
        //when(cityDao.findById(clientRequestDto.getCityId())).thenReturn();
        assertThrows(ValidationException.class, () -> userService.addClient(clientRequestDto));*/
    }
    @Test
    void getClientByDniOk() throws UserNotFoundException {
        when(userDao.findByDni("4333444")).thenReturn(java.util.Optional.ofNullable(user));
        User user2 = userService.getClientByDni("4333444");
        assertEquals(user2,user);
    }
    @Test
    void getClientByDniUserNotFound() throws UserNotFoundException {
       /* when(userDao.findByDni("4333444")).thenReturn(null);
//        User user2 = userService.getClientByDni("4333444");
//        assertEquals(user2,user);
        assertThrows(UserNotFoundException.class, () -> userService.getClientByDni("4333444"));*/
    }

    @Test
    void getAllClientsOk() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userDao.findAllClients()).thenReturn(users);
        List<User> users2 = userDao.findAllClients();
        assertEquals(users.get(0),users2.get(0));
        assertEquals(users.size(),users2.size());
    }

    @Test
    void getAllClientsEmpty(){
        List<User> users = new ArrayList<>();
        when(userDao.findAllClients()).thenReturn(users);
        List<User> users2 = userDao.findAllClients();
        assertEquals(users.isEmpty(),users2.isEmpty());
        assertEquals(users.size(),users2.size());
    }

    @Test
    void updateClient() throws UserNotFoundException {

        when(cityDao.findById(user.getCity().getCityId())).thenReturn(java.util.Optional.ofNullable(city));
        when(userDao.findByDni(user.getDni())).thenReturn(java.util.Optional.ofNullable(user));
        doNothing().when(userDao).save(user);
        userService.updateClient("123",user);
        verify(userDao, times(1)).save(user);
    }

    @Test
    void deleteClientOk() {
        /*when(userDao.findByDni(clientRequestDto.getDni())).thenReturn(java.util.Optional.ofNullable(user));
        doNothing().when(userDao).delete(user);*/

    }
}