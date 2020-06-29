package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.exceptions.CityNotFoundException;
import edu.utn.utnphones.exceptions.TarriffAlreadyExistsException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TariffController {

    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    public Tariff createTariff (TariffRequestDto newTariff) throws CityNotFoundException, ValidationException, TarriffAlreadyExistsException {
        return tariffService.createTariff(newTariff);
    }


    public List<Tariff> readTariff(){
        return  tariffService.readTariff();
    }


//
//    public void updateTariff (@RequestBody TariffRequestDto tariff){
//        tariffService.updateTariff(tariff);
//    }
//
//
//    public void deleteTariff(@PathVariable Integer idCityOrigin, @PathVariable Integer idCityDestination){
//        tariffService.deleteTariff(idCityOrigin,idCityDestination);
//    }





}

