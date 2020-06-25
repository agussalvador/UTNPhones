package edu.utn.utnphones.projection;

import edu.utn.utnphones.domain.City;

public interface TariffView {

     Long getTariffId();
     Double getCostPrice();
     Double getPrice();
     City getCityOrigin ();
     City getCityDestination ();
}
