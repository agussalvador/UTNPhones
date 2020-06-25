package edu.utn.utnphones.dto;

import edu.utn.utnphones.domain.enums.TypeLine;
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

    @Enumerated(EnumType.STRING)
    private TypeLine typeLine;

}
