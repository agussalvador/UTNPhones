package edu.utn.utnphones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill")
    private Integer billId;

    @Column(name = "count_calls")
    private Integer countCalls;

    @Column(name = "cost_price")
    private Double costPrice;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "date_bill")
    private LocalDateTime dateBill;

    @Column(name = "expirate_date")
    private LocalDateTime expirationDate;
/*
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "id_telephone_line")
    private PhoneLine phoneLine;


    @OneToMany(mappedBy = "bill")
    private List<Call> calls;*/

}
