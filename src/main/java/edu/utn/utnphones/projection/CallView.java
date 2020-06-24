package edu.utn.utnphones.projection;

import java.time.LocalDateTime;

public interface CallView {

    String getDni();

    String getPhoneNumberOrigin();
    String getPhoneNumberDestination();

    String getCityOrigin();
    String getCityDestination();

    Integer getDuration();

    LocalDateTime getDate();

}
