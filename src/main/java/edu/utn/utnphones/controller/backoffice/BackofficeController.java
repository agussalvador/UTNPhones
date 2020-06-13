package edu.utn.utnphones.controller.backoffice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backoffice")
public class BackofficeController {

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World Employee";
    }
}
