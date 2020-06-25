package edu.utn.utnphones.controller.backoffice;


import edu.utn.utnphones.controller.TariffController;
import edu.utn.utnphones.domain.User;

import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.exceptions.UserAlreadyExistsException;
import edu.utn.utnphones.projection.ClientView;
import edu.utn.utnphones.projection.TariffView;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/backoffice/tariff")
public class TariffBackOfficeController {

    private final TariffController tariffController;
    private final SessionManager sessionManager;

    @Autowired
    public TariffBackOfficeController(TariffController tariffController, SessionManager sessionManager) {
        this.tariffController = tariffController;
        this.sessionManager = sessionManager;
    }


    @PostMapping
    public ResponseEntity<User> createTariff(@RequestHeader("Authorization") String sessionToken, @RequestBody TariffRequestDto newTariff) {

        try {
            tariffController.createTariff(newTariff);
            return ResponseEntity.accepted().build();
        } catch (JpaSystemException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TariffView>> readTariff(@RequestHeader("Authorization") String sessionToken){

        List<TariffView> tariffs = new ArrayList<>();
        try{
            tariffs = tariffController.readTariff();
            return (tariffs.size() > 0) ? ResponseEntity.ok(tariffs) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity updateTariff(@RequestHeader("Authorization") String sessionToken, @RequestBody TariffRequestDto tariff){

        try{
            tariffController.updateTariff(tariff);
            return ResponseEntity.accepted().build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{idCityOrigin}/{idCityDestination}")
    public ResponseEntity deleteTariff(@RequestHeader("Authorization") String sessionToken, @PathVariable Integer idCityOrigin, @PathVariable Integer idCityDestination){

        try{
            tariffController.deleteTariff(idCityOrigin,idCityDestination);
            return ResponseEntity.ok().build();
        }catch (JpaSystemException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
