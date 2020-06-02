package edu.utn.utnphones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue(value="Employee")
public class Employee extends Person {

    @Column(name = "pwd")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

}
