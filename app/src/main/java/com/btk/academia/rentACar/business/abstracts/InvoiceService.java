package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.InvoiceDto;
import com.btk.academia.rentACar.business.dtos.RentalDto;
import com.btk.academia.rentACar.core.utilities.results.DataResult;

import java.util.List;

public interface InvoiceService {
    DataResult<InvoiceDto> get(Integer rentalId);
}
