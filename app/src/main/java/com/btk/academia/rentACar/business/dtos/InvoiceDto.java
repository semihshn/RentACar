package com.btk.academia.rentACar.business.dtos;

import com.btk.academia.rentACar.entities.concretes.AdditionalService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private List<AdditionalServiceDto> additionalServiceDtos;
    private Double total;
}
