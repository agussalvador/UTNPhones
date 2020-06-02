package edu.utn.utnphones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue(value="Client")
public class Client extends Person {

    @Column(name = "pwd")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "client")
    private List<PhoneLine> phonelines;

    //private List<Bill> bills;



}
