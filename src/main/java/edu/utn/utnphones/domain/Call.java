package edu.utn.utnphones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue()
    @Column(name = "id_call")
    private Long callId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_telephone_origin")
    @Fetch(FetchMode.JOIN)
    private PhoneLine phoneLineOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_telephone_destination")
    @Fetch(FetchMode.JOIN)
    private PhoneLine phoneLineDestination;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_origin")
    @Fetch(FetchMode.JOIN)
    private City cityOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_destination")
    @Fetch(FetchMode.JOIN)
    private City cityDestination;

    @Column(name = "duration")
    private Integer duration; // en segundos

    @Column(name = "call_date")
    private LocalDateTime callDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "id_bill")
    @Fetch(FetchMode.JOIN)
    private Bill bill;

}
