package edu.utn.utnphones.controller;

import edu.utn.utnphones.dto.PhoneLineRequestDto;
import edu.utn.utnphones.exceptions.PhoneLineAlreadyExistsException;
import edu.utn.utnphones.service.PhoneLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PhoneLineController {

    private final PhoneLineService phoneLineService;

    @Autowired
    public PhoneLineController(PhoneLineService phoneLineService) {
        this.phoneLineService = phoneLineService;
    }

    // Create PhoneLine By User Id
    public void addPhoneLine (PhoneLineRequestDto phoneLineDto) throws PhoneLineAlreadyExistsException {
        phoneLineService.addPhoneLine();
    }

    // Update PhoneLine By User Dni

    // Delete PhoneLine By User Dni

    // Get PhoneLine By Id

    // Get PhoneLine by User Dni

    // Get PhoneLines By User Dni

}
