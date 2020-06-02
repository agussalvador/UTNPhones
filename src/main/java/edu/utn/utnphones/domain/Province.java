package edu.utn.utnphones.domain;

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
@Table(name = "provinces")
public class Province implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_province")
    private Integer provinceId;

    @Column(name = "province_name")
    private String name;

    @OneToMany(mappedBy = "province")
    private List<City> cities;


}
