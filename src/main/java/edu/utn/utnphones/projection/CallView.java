package edu.utn.utnphones.projection;

import java.time.LocalDateTime;

public interface CallView {

    String getFullNumberOrigin();
    String getFullNumberDestination();

    String getCityOrigin();
    String getCityDestination();

    Integer getDuration();

    LocalDateTime getDate();

    Long getIdBill();


}
