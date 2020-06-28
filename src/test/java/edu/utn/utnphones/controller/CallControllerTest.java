package edu.utn.utnphones.controller;

import edu.utn.utnphones.controller.backoffice.CallsBackOficeController;
import edu.utn.utnphones.domain.*;
import edu.utn.utnphones.dto.CallRequestDto;
import edu.utn.utnphones.exceptions.CallAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.CallView;
import edu.utn.utnphones.service.CallService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallControllerTest {

    CallController callController;

    CallRequestDto callRequestDto;

    CallView callView;
    List<CallView> callViewList;

    //Dates
    String from;
    String to;
    Date fromDate;
    Date toDate;

    @Mock
    CallService callService;

    @Before
    public void setUp() throws ParseException {
        initMocks(this);
        callController = new CallController(callService);
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        callView = factory.createProjection(CallView.class);

        callView.setDni("32165498");
        callView.setPhoneNumberOrigin("022365899633");
        callView.setPhoneNumberDestination("02203658896");
        callView.setCityOrigin("Mar del Plata");
        callView.setCityDestination("Merlo");
        callView.setDuration(125);
        callView.setDate(new Date());

        List<CallView> callViewList = new ArrayList<>();
        callViewList.add(callView);

        from = "01/02/2003";
        to = "09/12/2018";
        fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(from);
        toDate = new SimpleDateFormat("dd/MM/yyyy").parse(to);
    }

    /* test method : addCall */
    @Test
    public void addCallOk() throws CallAlreadyExistsException, ParseException, ValidationException {

        Call call = new Call();
        when(callService.addCall(callRequestDto)).thenReturn(call);
        Call callReturned = callController.addCall(callRequestDto);
        assertEquals(call, callReturned);
    }

    /* test method : callByDni */
    @Test
    public void testCallByDniOk() throws UserNotFoundException, ValidationException {

        when(callService.getCallsByDni("32165498")).thenReturn(callViewList);

        List<CallView> callViewsReturned = callController.getCallsByDni("32165498");

        assertEquals(callViewList, callViewsReturned);
        verify(callService, times(1)).getCallsByDni("32165498");
    }

    @Test(expected = UserNotFoundException.class)
    public void testCallByDniUserNotFound() throws ValidationException, UserNotFoundException {

        when(callService.getCallsByDni("321654987")).thenThrow(UserNotFoundException.class);
        callController.getCallsByDni("321654987");
    }

    @Test(expected = ValidationException.class)
    public void testCallByDniInvalidData() throws UserNotFoundException, ValidationException {
        callController.getCallsByDni("");
    }

    /* test method : getCallsByUserFilterByDate */
    @Test
    public void getCallsByUserFilterByDateOk() throws UserNotFoundException, ValidationException {

        when(callService.getCallsByUserFilterByDate("32165498", fromDate, toDate )).thenReturn(callViewList);

        List<CallView> callViewsReturned = callController.getCallsByUserFilterByDate("32165498", fromDate, toDate);

        assertEquals(callViewList, callViewsReturned);
        verify(callService, times(1)).getCallsByUserFilterByDate("32165498", fromDate, toDate);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetCallsByUserFilterByDateUserNotFound() throws UserNotFoundException, ValidationException {

        when(callService.getCallsByUserFilterByDate("12345678", fromDate, toDate)).thenThrow(UserNotFoundException.class);
        callController.getCallsByUserFilterByDate("12345678", fromDate, toDate);
    }

    @Test(expected = ValidationException.class)
    public void testGetCallsByUserFilterByDateInvalidData() throws ValidationException, UserNotFoundException {

        callController.getCallsByUserFilterByDate("",fromDate, toDate);
    }

    /* test method : getTOP10MostCalledDestination */
    @Test
    public void testGetTOP10MostCalledDestinationOk(){

        City city = new City((long) 3,"Mar del Plata", "0223", new Province((long)1 ,"Buenos Aires"));
        List<City> cities = new ArrayList<>();
        cities.add(city);

        when(callService.getTOP10MostCalledDestination((long)45)).thenReturn(cities);

        List<City>returnedCities = callController.getTOP10MostCalledDestination((long)45);

        assertEquals(cities, returnedCities);
        verify(callService, times(1)).getTOP10MostCalledDestination((long)45);
    }


    /* test method : getLast3CallsByDni */
    @Test
    public void testGetLast3CallsByDniOk() throws UserNotFoundException, ValidationException {
        when(callService.getLast3CallsByDni("32165498")).thenReturn(callViewList);

        List<CallView> callViewsReturned = callController.getLast3CallsByDni("32165498");

        assertEquals(callViewList, callViewsReturned);
        verify(callService, times(1)).getLast3CallsByDni("32165498");
    }


    @Test(expected = UserNotFoundException.class)
    public void testGetLast3CallsByDniUserNotFound() throws UserNotFoundException, ValidationException {

        when(callService.getLast3CallsByDni("321654987")).thenThrow(UserNotFoundException.class);
        callController.getLast3CallsByDni("321654987");
    }

    @Test(expected = ValidationException.class)
    public void testGetLast3CallsByDniInvalidData() throws UserNotFoundException, ValidationException {
        callController.getLast3CallsByDni("");
    }


}