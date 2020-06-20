package edu.utn.utnphones.controller.backoffice;

import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tariff")
public class TariffController {

    private static TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    /*CRUD tariff*/
    @PostMapping("/")
    public void createTariff (@RequestBody Tariff tariff){
        tariffService.createTariff(tariff);
    }

    @GetMapping("/")
    public List<Tariff> readTariff(){
        return  tariffService.readTariff();
    }

    @PutMapping("/")
    public void updateTariff (@RequestBody Tariff tariff){
        tariffService.updateTariff(tariff);
    }

    @DeleteMapping("/{idCityOrigin}/{idCityDestination}")
    public void deleteTariff(@PathVariable Integer idCityOrigin, @PathVariable Integer idCityDestination){
        tariffService.deleteTariff(idCityOrigin,idCityDestination);
    }
}

