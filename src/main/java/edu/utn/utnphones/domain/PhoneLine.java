package edu.utn.utnphones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "telephone_lines")
public class PhoneLine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telephone_line")
    private Integer personId;

    @Column(name = "phone_number")
    private String number;

    @Column(name = "type_line")
    private String type;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "id_user")
    private Client client;

    /*
    @OneToMany(mappedBy = "phoneLine")
    private List<Bill> bills;*/



}
