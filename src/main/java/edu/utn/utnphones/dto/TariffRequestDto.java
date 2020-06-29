package edu.utn.utnphones.dto;

import edu.utn.utnphones.domain.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TariffRequestDto {

    private Double costPrice;
    private Double price;
    private Long cityOriginId;
    private Long cityDestinationId;
}