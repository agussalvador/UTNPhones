package edu.utn.utnphones.service;

import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.dto.CallRequestDto;
import edu.utn.utnphones.exceptions.CallAlreadyExistsException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.CallView;
import edu.utn.utnphones.repository.CallDao;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class CallService {

    private final CallDao callDao;
    private final UserService userService;

    @Autowired
    public CallService(CallDao callDao, UserService userService) {
        this.callDao = callDao;
        this.userService = userService;
    }

    public Call addCall(CallRequestDto call) throws ValidationException, CallAlreadyExistsException {
        if(!call.isValid()) throw new ValidationException("Error - does not include all necessary information ");
        Long idCall = callDao.saveCall(call.getNumberOrigin(), call.getNumberDestination(), call.getDuration(), call.getDate() );
        return callDao.findById(idCall).orElseThrow( () -> new CallAlreadyExistsException());
    }

    public List<CallView> getCallsByDni(String dni) throws JpaSystemException, UserNotFoundException {
        userService.getClientByDni(dni);
        return callDao.getCallsByDni(dni);
    }

    public List<CallView> getCallsByUserFilterByDate(String dni, Date from, Date to) {
        return callDao.getCallsByUserFilterByDate(dni, LocalDateTime.ofInstant(from.toInstant(),
                ZoneId.systemDefault()), LocalDateTime.ofInstant(to.toInstant(),
                ZoneId.systemDefault()));
    }

    public List<City> getTOP10MostCalledDestination(String dni) {
        return callDao.getTOP10MostCalledDestination(dni);
    }


    /*Parcial - Laborarotio V -  01-06-2020  */
    public List<CallView> getLast3CallsByDni( String dni ) throws UserNotFoundException {
        userService.getClientByDni(dni);
        return callDao.getLast3CallsByDni(dni);
    }

}
