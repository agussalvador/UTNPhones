package edu.utn.utnphones.dto;

import edu.utn.utnphones.domain.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientPhoneLineRequestDto {

    private String firstname;

    private String lastname;

    private String dni;

    private String password;

    private City city;

    private String typeLine;

}
