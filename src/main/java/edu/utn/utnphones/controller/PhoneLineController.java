package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.dto.PhoneLineRequestDto;
import edu.utn.utnphones.projection.PhoneLineView;
import edu.utn.utnphones.service.PhoneLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PhoneLineController {

    private final PhoneLineService phoneLineService;

    @Autowired
    public PhoneLineController(PhoneLineService phoneLineService) {
        this.phoneLineService = phoneLineService;
    }

    // Create PhoneLine By User Id
    public PhoneLine addPhoneLine (PhoneLineRequestDto newPhoneLine) throws JpaSystemException {
        return phoneLineService.addPhoneLine(newPhoneLine);
    }

    // Get PhoneLine By Id

    // Get PhoneLines by User Dni
    public List<PhoneLineView> getPhoneLinesByUserDni(String dni) throws JpaSystemException{
        return phoneLineService.getPhoneLinesByUserDni(dni);
    }


    // Update PhoneLine By User Dni

    // Delete PhoneLine By User Dni


}
