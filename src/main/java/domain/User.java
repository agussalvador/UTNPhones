package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    private String name;
    private String surname;
    private String dni;
    private String password;
    private City city;
    private State state;
    private Boolean enabled;

}
