package edu.utn.utnphones.dto;

import edu.utn.utnphones.domain.TypeLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientRequestDto implements Serializable {

    private Long cityId;

    private String firstname;

    private String lastname;

    private String dni;

    private String password;

    @Enumerated(EnumType.STRING)
    private TypeLine typeLine;

}
