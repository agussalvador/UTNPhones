package edu.utn.utnphones.service;

import edu.utn.utnphones.repository.CallDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {

    private final CallDao callDao;
    private final UserService userService;

    @Autowired
    public CallService(CallDao callDao, UserService userService) {
        this.callDao = callDao;
        this.userService = userService;
    }









}
