package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btk.academia.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btk.academia.rentACar.core.utilities.results.DataResult;

public interface InvoiceService {
    DataResult<InvoiceIndividualCustomerDto> getInvoiceOfIndividualCustomer(Integer rentalId);
    DataResult<InvoiceCorporateCustomerDto> getInvoiceOfCorporateCustomer(Integer rentalId);
}
