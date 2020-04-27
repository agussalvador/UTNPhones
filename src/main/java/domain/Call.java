package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Call {

    private PhoneLine phoneFrom;
    private PhoneLine phoneTo;
    private City cityFrom;
    private City cityTo;
    private Long duration;
    private Bill bill;
    private Long priceByMinutes;
    private Long priceCall;
    private Date date;
}
