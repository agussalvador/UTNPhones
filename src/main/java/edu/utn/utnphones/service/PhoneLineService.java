package edu.utn.utnphones.service;

import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.dto.PhoneLineRequestDto;
import edu.utn.utnphones.projection.PhoneLineView;
import edu.utn.utnphones.repository.PhoneLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneLineService {

    private final PhoneLineDao phoneLineDao;

    @Autowired
    public PhoneLineService(PhoneLineDao phoneLineDao) {
        this.phoneLineDao = phoneLineDao;
    }

    // Create PhoneLine By User Id
    public PhoneLine addPhoneLine (PhoneLineRequestDto newPhoneLine) throws JpaSystemException {

        Long idPhoneLine = phoneLineDao.generateNumber(newPhoneLine.getDni(), newPhoneLine.getTypeLine().name());
        return phoneLineDao.getOne(idPhoneLine);
    }


    // get PhoneLine By Id



    // get PhoneLines by User Dni
    public List<PhoneLineView> getPhoneLinesByUserDni(String dni)throws JpaSystemException{
        return phoneLineDao.getPhoneLinesByDni(dni);
    }


    // Update PhoneLine





    // Delete PhoneLine By User Dni






}
