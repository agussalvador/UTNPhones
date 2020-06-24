package edu.utn.utnphones.service;

import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.projection.CallView;
import edu.utn.utnphones.projection.MostCalledDestinationView;
import edu.utn.utnphones.repository.CallDao;
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

    public List<CallView> getCallsByDni(String dni) throws JpaSystemException, UserNotFoundException {
        userService.getClientByDni(dni);
        return callDao.getCallsByDni(dni);
    }


    public List<CallView> getCallsByUserFilterByDate(String dni, Date from, Date to)  throws JpaSystemException {
        return callDao.getCallsByUserFilterByDate(dni, LocalDateTime.ofInstant(from.toInstant(),
                ZoneId.systemDefault()), LocalDateTime.ofInstant(to.toInstant(),
                ZoneId.systemDefault()));
    }


    public List<MostCalledDestinationView> getTOP10MostCalledDestination(String dni) throws JpaSystemException {
        return callDao.getTOP10MostCalledDestination(dni);
    }


    /*Parcial  06-01-2020  */
    public List<CallView> getLast3CallsByDni( String dni ) throws UserNotFoundException {

        userService.getClientByDni(dni);
        return callDao.getLast3CallsByDni(dni);
    }


}
