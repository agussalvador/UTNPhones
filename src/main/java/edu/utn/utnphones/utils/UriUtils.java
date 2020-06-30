package edu.utn.utnphones.utils;

import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.domain.User;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class UriUtils {

    public static URI getLocation(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

}
