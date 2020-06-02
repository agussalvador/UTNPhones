package edu.utn.utnphones.service;

import edu.utn.utnphones.dto.ClientPhoneLineRequestDto;
import edu.utn.utnphones.exceptions.ClientAlreadyExistsException;
import edu.utn.utnphones.projection.ClientCallProjection;
import edu.utn.utnphones.projection.LastCalls;
import edu.utn.utnphones.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService{

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(ClientPhoneLineRequestDto clientPhoneLineRequestDto) throws ClientAlreadyExistsException {
        clientRepository.addClient(clientPhoneLineRequestDto.getCity().getCityId(), clientPhoneLineRequestDto.getFirstname(), clientPhoneLineRequestDto.getLastname(), clientPhoneLineRequestDto.getDni(), clientPhoneLineRequestDto.getPassword(), clientPhoneLineRequestDto.getTypeLine() );
    }


    /*Parcial  06-01-2020  */
    public List<LastCalls> getLast3CallsByDni( String dni ){
        return clientRepository.getLast3CallsByDni(dni);
    }




    /* Simulacro Parcial - endpoint : nombre, apellido, dni y destino mas llamado(ciudad y/o numero)   */
    public ClientCallProjection getMostCalledDestinationByDni (String dni){
        return clientRepository.getMostCalledDestinationByDni(dni);
    }

    /* TOP 10 destinos más llamados por el usuario. */
    public List<ClientCallProjection> getTOP10MostCalledDestinationByDni(String dni){
        return clientRepository.getTOP10MostCalledDestinationByDni(dni);
    }


}
