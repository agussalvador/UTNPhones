package edu.utn.utnphones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CallRequestDto {

    private String numberOrigin;
    private String numberDestination;
    private Integer duration;
    private LocalDateTime date;

}
