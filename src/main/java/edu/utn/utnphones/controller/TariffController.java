package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.projection.TariffView;
import edu.utn.utnphones.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TariffController {

    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    /*CRUD tariff*/
    public void createTariff ( TariffRequestDto newTariff)throws JpaSystemException {
        tariffService.createTariff(newTariff);
    }


    public List<TariffView> readTariff(){
        return  tariffService.readTariff();
    }


    public void updateTariff (@RequestBody TariffRequestDto tariff){
        tariffService.updateTariff(tariff);
    }


    public void deleteTariff(@PathVariable Integer idCityOrigin, @PathVariable Integer idCityDestination){
        tariffService.deleteTariff(idCityOrigin,idCityDestination);
    }





}

