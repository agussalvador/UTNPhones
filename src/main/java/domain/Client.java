package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client extends User {

    private HashSet<PhoneLine> phoneLines;
    private Boolean enabled;
    private ArrayList<Bill> bills;
}
