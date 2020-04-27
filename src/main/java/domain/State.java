package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class State {

    private String name;
    private HashSet<City> cities;
}
