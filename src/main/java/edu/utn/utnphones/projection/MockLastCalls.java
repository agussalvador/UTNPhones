package edu.utn.utnphones.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MockLastCalls implements LastCalls {

    private String dni;
    private String phoneLineDestination;
    private String cityDestination;
    private String dateCalls;


    @Override
    public String getDni() {
        return this.dni;
    }

    @Override
    public String getPhoneLineDestination() {
        return this.phoneLineDestination;
    }

    @Override
    public String getcityDestination() {
        return this.cityDestination;
    }

    @Override
    public String getDateCall() {
        return this.dateCalls;
    }
}
