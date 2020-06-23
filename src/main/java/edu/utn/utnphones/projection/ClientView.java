package edu.utn.utnphones.projection;


import javax.persistence.OneToMany;
import java.util.List;

public interface ClientView {

    String getDni();

    String getFullNumber();

    String getCityAndProvince();

    Integer getCountPhoneLines();



}
