package edu.utn.utnphones.controller;

import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
import edu.utn.utnphones.projection.CallView;
import edu.utn.utnphones.projection.MostCalledDestinationView;
import edu.utn.utnphones.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
public class CallController {

    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    public List<CallView> getCallsByDni(String dni) throws ValidationException, JpaSystemException, UserNotFoundException {

        if(!dni.isEmpty()){
            return callService.getCallsByDni(dni);
        }else{
            throw new ValidationException("dni have a value");
        }
    }

    public List<CallView> getCallsByUserFilterByDate(String dni, Date from, Date to) throws ValidationException, JpaSystemException {

        if(dni!=null){
            return callService.getCallsByUserFilterByDate(dni, from, to);
        }else{
            throw new ValidationException("dni have a value");
        }
    }

    public List<MostCalledDestinationView> getTOP10MostCalledDestination(String dni) throws ValidationException, JpaSystemException {

        if(dni!=null){
            return callService.getTOP10MostCalledDestination(dni);
        }else{
            throw new ValidationException("dni have a value");
        }
    }


    /*Parcial  06-01-2020  */
    public List<CallView> getLast3CallsByDni( String dni ) throws UserNotFoundException {

        return callService.getLast3CallsByDni(dni);
    }



}
