package edu.utn.utnphones.service;

import edu.utn.utnphones.repository.BillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    private final BillDao billDao;
    private final UserService userService;

    @Autowired
    public BillService(BillDao billDao, UserService userService) {
        this.billDao = billDao;
        this.userService = userService;
    }







}
