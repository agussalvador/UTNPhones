package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.domain.enums.Role;
import edu.utn.utnphones.domain.enums.TypeLine;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

class UserControllerTest {

    UserController userController;
    User user;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userController = new UserController(userService);
        user = new User((long) 1, "4333444", "santiago", "labatut", "pwd123", true, Role.client, null);

    }

    @Test
    void loginOk() throws UserNotFoundException, ValidationException {
        when(userService.login("123","pwd")).thenReturn(user);
        User user1 = userController.login("123","pwd");
        assertEquals(user1,user);
    }
    @Test
    void loginDniNull() throws UserNotFoundException, ValidationException {

        assertThrows(ValidationException.class, () -> userController.login(null,"pwd"));
    }
    @Test
    void loginPwdNull() throws UserNotFoundException, ValidationException {
        assertThrows(ValidationException.class, () -> userController.login("123",null));
    }
    @Test
    void loginUserNotFound() throws UserNotFoundException, ValidationException {
        when(userService.login("123","pwd")).thenThrow(new UserNotFoundException());
        assertThrows(UserNotFoundException.class, () -> userController.login("123","pwd"));
    }


    @Test
    void addClientOk() throws UserAlreadyExistsException, CityNotFoundException, ValidationException {

        ClientRequestDto clientRequestDto = new ClientRequestDto((long) 1, "Santiago", "labatut", "4333444", TypeLine.home);
        when(userService.addClient(clientRequestDto)).thenReturn(user);
        User user1 = userController.addClient(clientRequestDto);
        assertEquals(user1,user);
    }

    @Test
    void addClientUserAlreadyExist() throws UserAlreadyExistsException, CityNotFoundException, ValidationException {
        ClientRequestDto client =new ClientRequestDto();
        when(userService.addClient(client)).thenThrow((new UserAlreadyExistsException("Already exist")));
        assertThrows(UserAlreadyExistsException.class, () -> userController.addClient(client));

    }

    @Test
    void getClientByDniOk() throws UserNotFoundException, ValidationException {
        when(userService.getClientByDni("22")).thenReturn(user);
        User user1 = userController.getClientByDni("22");
        assertEquals(user1,user);
    }
    @Test
    void getClientByDniEmpty() {
        assertThrows(ValidationException.class, () -> userController.getClientByDni(""));
    }

    @Test
    void getClientByDniNull() {
        assertThrows(ValidationException.class, () -> userController.getClientByDni(null));
    }

    @Test
    void getAllClientsOk() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userService.getAllClients()).thenReturn(users);
        List<User> users2 = userController.getAllClients();
        assertEquals(users.get(0),users2.get(0));
        assertEquals(users.size(),users2.size());
    }

    @Test
    void updateClientOk() throws UserNotFoundException {
        doNothing().when(userService).updateClient("123",user);
        userController.updateClient("123",user);
        verify(userService, times(1)).updateClient("123",user);
    }

    @Test
    void deleteClient() throws UserNotFoundException {
        doNothing().when(userService).deleteClient("123");
        userController.deleteClient("123");
        verify(userService, times(1)).deleteClient("123");
    }
}

