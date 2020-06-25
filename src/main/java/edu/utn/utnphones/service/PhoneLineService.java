package edu.utn.utnphones.service;

import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.dto.PhoneLineRequestDto;
import edu.utn.utnphones.exceptions.PhoneLineNotFoundException;
import edu.utn.utnphones.exceptions.UserNotFoundException;
import edu.utn.utnphones.projection.PhoneLineView;
import edu.utn.utnphones.repository.PhoneLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneLineService {

    private final PhoneLineDao phoneLineDao;
    private final UserService userService;

    @Autowired
    public PhoneLineService(PhoneLineDao phoneLineDao, UserService userService) {
        this.phoneLineDao = phoneLineDao;
        this.userService = userService;
    }

    // Create PhoneLine By User Id
    public PhoneLine addPhoneLine (PhoneLineRequestDto newPhoneLine) throws JpaSystemException {
        Long idPhoneLine = phoneLineDao.generateNumber(newPhoneLine.getDni(), newPhoneLine.getTypeLine().name());
        return phoneLineDao.getOne(idPhoneLine);
    }

    // get PhoneLines by User Dni
    public List<PhoneLineView> getPhoneLinesByUserDni(String dni) throws UserNotFoundException {
        userService.getClientByDni(dni);
        return phoneLineDao.getPhoneLinesByDni(dni);
    }

    // get All PhoneLines
    public List<PhoneLineView> getPhoneLines()  {
        return phoneLineDao.getPhoneLines();
    }

    // Delete PhoneLine By User Dni
    public PhoneLine deletePhoneLine(Long idPhoneLine) throws PhoneLineNotFoundException {
        PhoneLine phoneLine = phoneLineDao.findById(idPhoneLine).orElse(null);
        if(phoneLine != null && phoneLine.getEnabled()) {
            phoneLineDao.suspendPhoneLine(idPhoneLine);
            return phoneLineDao.getOne(idPhoneLine);
        }else{
            throw new PhoneLineNotFoundException("Error: unable to delete phone line.");
        }
    }

    // Update PhoneLine (active line suspendida)
    public PhoneLine updatePhoneLine(Long idPhoneLine) throws PhoneLineNotFoundException {
        PhoneLine phoneLine = phoneLineDao.findById(idPhoneLine).orElse(null);
        if(phoneLine != null && !phoneLine.getEnabled()) {
            phoneLineDao.activePhoneLine(idPhoneLine);
            return phoneLineDao.getOne(idPhoneLine);
        }else{
            throw new PhoneLineNotFoundException("Error: unable to update phone line.");
        }
    }

}
