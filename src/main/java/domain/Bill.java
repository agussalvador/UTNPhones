package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bill {
    private PhoneLine phoneLine;
    private Client client;
    private Double totalPrice;
    private Date date;
    private Date dateExpiration;
    private ArrayList<Call> calls;
}
