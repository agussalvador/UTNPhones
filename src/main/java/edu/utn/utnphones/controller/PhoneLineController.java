package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.dto.PhoneLineRequestDto;
import edu.utn.utnphones.exceptions.PhoneLineNotFoundException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.exceptions.ValidationException;
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
    public PhoneLine addPhoneLine (PhoneLineRequestDto newPhoneLine) throws JpaSystemException, ValidationException, UserNotFoundException {
        return phoneLineService.addPhoneLine(newPhoneLine);
    }

    // Get PhoneLines by User Dni
    public List<PhoneLineView> getPhoneLinesByUserDni(String dni) throws ValidationException, UserNotFoundException {
        if (!dni.isEmpty()){
            return phoneLineService.getPhoneLinesByUserDni(dni);
        }else {
            throw new ValidationException("dni must have a value");
        }
    }

    public List<PhoneLineView> getPhoneLines() {
        return phoneLineService.getPhoneLines();
    }

    // Delete PhoneLine
    public PhoneLine deletePhoneLine(Long idPhone) throws PhoneLineNotFoundException {
        return phoneLineService.deletePhoneLine(idPhone);
    }

    // Update PhoneLine By User Dni
    public PhoneLine updatePhoneLine(Long idPhone) throws PhoneLineNotFoundException {
        return phoneLineService.updatePhoneLine(idPhone);
    }

}
