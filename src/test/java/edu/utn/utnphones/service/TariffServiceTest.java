package edu.utn.utnphones.service;

import edu.utn.utnphones.controller.TariffController;
import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.domain.enums.Role;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.TariffDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

class TariffServiceTest {

    TariffService tariffService;
    Tariff tariff;
    City city;
    TariffRequestDto tariffRequestDto;
    @Mock
    CityDao cityDao;
    @Mock
    TariffDao tariffDao;

    @BeforeEach
    void setUp() {
        initMocks(this);
        tariffService = new TariffService(tariffDao,cityDao);
        city = new City((long)1,"Mar del plata","0223",null);

    }

    @Test
    void createTariffOk() {

    }

    @Test
    void readTariffOk() {
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        when(tariffDao.getAllTariffs()).thenReturn(tariffs);
        List<Tariff> tariffs2 = tariffService.readTariff();
        assertEquals(tariffs.get(0),tariffs2.get(0));
        assertEquals(tariffs.size(),tariffs2.size());
    }

    @Test
    void updateTariffOk() {
        tariffRequestDto = new TariffRequestDto(6.0,3.0,(long)4,(long)7) ;
        doNothing().when(tariffDao).update(tariffRequestDto.getCityOriginId(), tariffRequestDto.getCityDestinationId(),
                tariffRequestDto.getCostPrice(),tariffRequestDto.getPrice());
        tariffService.updateTariff(tariffRequestDto);
        verify(tariffDao, times(1)).update(tariffRequestDto.getCityOriginId(), tariffRequestDto.getCityDestinationId(),
                tariffRequestDto.getCostPrice(),tariffRequestDto.getPrice());
    }

    @Test
    void updateTariffException() {

    }

    @Test
    void deleteTariff() {
        doNothing().when(tariffDao).delete(1,2);
        tariffService.deleteTariff(1,2);
        verify(tariffDao, times(1)).delete(1,2);
    }
}