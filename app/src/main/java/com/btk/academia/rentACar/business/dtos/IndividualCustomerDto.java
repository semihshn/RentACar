package com.btk.academia.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;
}
