package edu.utn.utnphones.service;

import edu.utn.utnphones.exceptions.PhoneLineAlreadyExistsException;
import edu.utn.utnphones.repository.PhoneLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneLineService {

    private final PhoneLineDao phoneLineDao;

    @Autowired
    public PhoneLineService(PhoneLineDao phoneLineDao) {
        this.phoneLineDao = phoneLineDao;
    }

    // Create PhoneLine By User Id
    public void addPhoneLine () throws PhoneLineAlreadyExistsException {

    }

    // Update PhoneLine



    // Delete PhoneLine By User Dni


    // get PhoneLine By Id


    // get PhoneLine by User Dni


    // get PhoneLines By User Dni


}
