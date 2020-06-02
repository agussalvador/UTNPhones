package edu.utn.utnphones.controller;

import edu.utn.utnphones.dto.ClientPhoneLineRequestDto;
import edu.utn.utnphones.exceptions.ClientAlreadyExistsException;
import edu.utn.utnphones.exceptions.ValidateDniException;
import edu.utn.utnphones.projection.ClientCallProjection;
import edu.utn.utnphones.projection.LastCalls;
import edu.utn.utnphones.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;


    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping
    public void registerClient(@RequestBody ClientPhoneLineRequestDto clientPhoneLineRequestDto) throws ClientAlreadyExistsException {
       clientService.addClient(clientPhoneLineRequestDto);
    }

    //todo remove client

    //todo update client


    /*Parcial  06-01-2020  */
    @GetMapping("/last-calls")
    @ResponseBody
    public List<LastCalls> getLast3CallsByDni(@RequestParam String dni ){
        return clientService.getLast3CallsByDni(dni);
    }





    /* Simulacro Parcial - endpoint : nombre, apellido, dni y destino mas llamado(ciudad y/o numero)   */
    @GetMapping("/most-called-destination")
    @ResponseBody
    public ClientCallProjection getMostCalledDestinationByDni(@RequestParam String dni) throws ValidateDniException {
        return clientService.getMostCalledDestinationByDni(dni);
    }

    @GetMapping("/top10-most-called-destination")
    @ResponseBody
    /* TOP 10 destinos más llamados por el usuario. */
    public List<ClientCallProjection> getTOP10MostCalledDestinationByDni(@RequestParam String dni){
        return clientService.getTOP10MostCalledDestinationByDni(dni);
    }






}
