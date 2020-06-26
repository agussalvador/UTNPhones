package edu.utn.utnphones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.StringUtils;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CallRequestDto {

    private String numberOrigin;
    private String numberDestination;
    private Integer duration;
    private LocalDateTime date;

    public Boolean isValid(){
        return  !StringUtils.isBlank(numberOrigin) && !StringUtils.isBlank(numberDestination) && duration != null && date != null ;
    }

}
