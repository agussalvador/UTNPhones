package edu.utn.utnphones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.utn.utnphones.domain.enums.TypeLine;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "telephone_lines")
public class PhoneLine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telephone_line")
    private Long phoneLineId;

    @Column(name = "phone_number")
    private String number;

    @Column(name = "type_line")
    @Enumerated(EnumType.STRING)
    private TypeLine typeLine;

    @JsonIgnore
    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

}
