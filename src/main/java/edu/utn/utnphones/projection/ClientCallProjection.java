package edu.utn.utnphones.projection;

public interface ClientCallProjection {

    String getDni();

    String getFullName();

    String getCityOrigin();

    String getPhoneLineOrigin();

    String getCityDestination();

    String getPhoneLineDestination();

    Integer getCountCalls();


}
