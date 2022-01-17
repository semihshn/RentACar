package com.btk.academia.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceCorporateCustomerDto {
    private String companyName;
    private String taxNumber;
    private List<AdditionalServiceDto> additionalServiceDtos;
    private Double total;
}
