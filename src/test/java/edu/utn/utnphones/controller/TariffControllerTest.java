package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.domain.enums.Role;
import edu.utn.utnphones.domain.enums.TypeLine;
import edu.utn.utnphones.dto.ClientRequestDto;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.TarriffAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.service.TariffService;
import edu.utn.utnphones.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

class TariffControllerTest {

    TariffController tariffController;
    Tariff tariff;
    TariffRequestDto tariffRequestDto;
    @Mock
    TariffService tariffService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        tariffController = new TariffController(tariffService);
        tariff = new Tariff();
    }

    @Test
    void createTariffOk() throws TarriffAlreadyExistsException, CityNotFoundException, ValidationException {
        TariffRequestDto tariffRequestDto = new TariffRequestDto(6.0,3.0,(long)4,(long)7);
        when(tariffService.createTariff(tariffRequestDto)).thenReturn(tariff);
        Tariff tariff1 = tariffController.createTariff(tariffRequestDto);
        assertEquals(tariff1,tariff);
    }

    @Test
    void createTariffAlreadyExist() throws TarriffAlreadyExistsException, CityNotFoundException, ValidationException {

        TariffRequestDto tariffRequestDto = new TariffRequestDto(6.0,3.0,(long)4,(long)7);
        when(tariffService.createTariff(tariffRequestDto)).thenThrow((new TarriffAlreadyExistsException("Already exist")));
        assertThrows(TarriffAlreadyExistsException.class, () -> tariffController.createTariff(tariffRequestDto));
    }

    @Test
    void createTariffCityNotFound() throws TarriffAlreadyExistsException, CityNotFoundException, ValidationException {

        TariffRequestDto tariffRequestDto = new TariffRequestDto(6.0,3.0,(long)4,(long)7);
        when(tariffService.createTariff(tariffRequestDto)).thenThrow((new CityNotFoundException()));
        assertThrows(CityNotFoundException.class, () -> tariffController.createTariff(tariffRequestDto));
    }

    @Test
    void readTariffOk() {

        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        when(tariffService.readTariff()).thenReturn(tariffs);
        List<Tariff> tariffs2 = tariffController.readTariff();
        assertEquals(tariffs.get(0),tariffs2.get(0));
        assertEquals(tariffs.size(),tariffs2.size());
    }

    @Test
    void updateTariffOk() {
        doNothing().when(tariffService).updateTariff(tariffRequestDto);
        tariffController.updateTariff(tariffRequestDto);
        verify(tariffService, times(1)).updateTariff(tariffRequestDto);

    }

    @Test
    void deleteTariff() {
        doNothing().when(tariffService).deleteTariff(1,2);
        tariffController.deleteTariff(1,2);
        verify(tariffService, times(1)).deleteTariff(1,2);
    }
}