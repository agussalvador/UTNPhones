package edu.utn.utnphones.controller.webApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web")
public class ClientController {

    @RequestMapping({ "/hello" })
    public String firstPage() { return "Hello World Client"; }

}
