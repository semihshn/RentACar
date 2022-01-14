package com.btk.academia.rentACar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="payment_date")
    private LocalDateTime paymentDate;

    @Column(name="total")
    private Double total;

    @ManyToOne
    @JoinColumn(name="rental_id")
    private Rental rental;
}
