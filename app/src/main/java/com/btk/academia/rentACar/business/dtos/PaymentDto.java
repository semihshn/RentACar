package com.btk.academia.rentACar.business.dtos;

import com.btk.academia.rentACar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private LocalDateTime paymentDate;
    private Double total;
    private Integer rentalId;
}
