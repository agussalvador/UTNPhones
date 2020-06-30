package edu.utn.utnphones.service;

import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.repository.CityDao;
import edu.utn.utnphones.repository.TariffDao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TariffServiceTest {

    TariffService tariffService;
    Tariff tariff;
    City city;
    City city2;
    TariffRequestDto tariffRequestDto;
    @Mock
    CityDao cityDao;
    @Mock
    TariffDao tariffDao;

    @Before
    public void setUp() {
        initMocks(this);
        tariffService = new TariffService(tariffDao,cityDao);
        city = new City((long)1,"Mar del plata","0223",null);
        city2 = new City((long)4,"Balcarce","2266",null);

    }

    @Test
    public void createTariffOk() {

    }

    @Test
    public void readTariffOk() {
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        when(tariffDao.getAllTariffs()).thenReturn(tariffs);
        List<Tariff> tariffs2 = tariffService.readTariff();
        assertEquals(tariffs.get(0),tariffs2.get(0));
        assertEquals(tariffs.size(),tariffs2.size());
    }

    @Test
    public void updateTariffOk() {
        tariffRequestDto = new TariffRequestDto(6.0,3.0,(long)4,(long)7) ;
        doNothing().when(tariffDao).update(tariffRequestDto.getCityOriginId(), tariffRequestDto.getCityDestinationId(),
                tariffRequestDto.getCostPrice(),tariffRequestDto.getPrice());
        tariffService.updateTariff(tariffRequestDto);
        verify(tariffDao, times(1)).update(tariffRequestDto.getCityOriginId(), tariffRequestDto.getCityDestinationId(),
                tariffRequestDto.getCostPrice(),tariffRequestDto.getPrice());
    }

    @Test
    public void updateTariffException() {

    }

    @Test
    public void deleteTariff() {
        doNothing().when(tariffDao).delete(1,2);
        tariffService.deleteTariff(1,2);
        verify(tariffDao, times(1)).delete(1,2);
    }


}