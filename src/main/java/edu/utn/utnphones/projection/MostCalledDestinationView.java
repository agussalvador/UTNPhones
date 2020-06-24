package edu.utn.utnphones.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface MostCalledDestinationView {

    String getCityDestination();

    Integer getCountCalls();

}
