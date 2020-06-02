package edu.utn.utnphones.controller;

import edu.utn.utnphones.exceptions.ValidateDniException;
import edu.utn.utnphones.projection.LastCalls;
import edu.utn.utnphones.projection.MockLastCalls;
import edu.utn.utnphones.service.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class ClientControllerTest {

    private ClientController clientController;
    private ClientService clientService;
    private List<LastCalls> lastCalls;
    private LastCalls mockLastCalls;


    @Before()
    public void setUp(){
        initMocks(this);
        clientService = mock(ClientService.class);
        clientController = new ClientController(clientService);
    }


    @Test
    public void testGetLast3CallsByDniOk() throws ValidateDniException{
        String mockDni = "12345";
        lastCalls = new ArrayList<LastCalls>();
        lastCalls.add(new MockLastCalls(mockDni, "0223 - 1236558", "Balcarce", "2020-03-20 00:00:00"));

        when(clientService.getLast3CallsByDni(mockDni)).thenReturn(lastCalls);

        Assert.assertEquals( clientController.getLast3CallsByDni(mockDni), lastCalls );
    }


}
