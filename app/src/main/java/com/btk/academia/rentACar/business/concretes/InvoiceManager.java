package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.*;
import com.btk.academia.rentACar.business.dtos.*;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceManager implements InvoiceService {

    private ModelMapperService modelMapperService;
    private AdditionalServiceService additionalServiceService;
    private PaymentService paymentService;
    private RentalService rentalService;
    private CorporateCustomerService corporateCustomerService;
    private IndividualCustomerService individualCustomerService;

    public InvoiceManager(ModelMapperService modelMapperService,
                          AdditionalServiceService additionalServiceService,
                          PaymentService paymentService,RentalService rentalService,
                          CorporateCustomerService corporateCustomerService,
                          IndividualCustomerService individualCustomerService) {
        this.modelMapperService = modelMapperService;
        this.additionalServiceService=additionalServiceService;
        this.paymentService=paymentService;
        this.rentalService=rentalService;
        this.corporateCustomerService=corporateCustomerService;
        this.individualCustomerService=individualCustomerService;
    }

    @Override
    public DataResult<InvoiceIndividualCustomerDto> getInvoiceOfIndividualCustomer(Integer rentalId) {
        List<PaymentDto> paymentDtos=this.paymentService.getByRentalId(rentalId).getData();
        List<AdditionalServiceDto> additionalServiceDtos = this.additionalServiceService.getByRentalId(rentalId).getData();
        RentalDto rentalDto = this.rentalService.getById(rentalId).getData();
        IndividualCustomerDto individualCustomerDto=this.individualCustomerService.getById(rentalDto.getCustomerId()).getData();

        Double total = paymentDtos.stream()
                .map(a->a.getTotal())
                .reduce((double) 0, (Double::sum));

        total = additionalServiceDtos.stream()
                .map(a->a.getPrice())
                .reduce((double) total, (Double::sum));

        InvoiceIndividualCustomerDto invoiceDto=InvoiceIndividualCustomerDto.builder()
                .firstName(individualCustomerDto.getFirstName())
                .lastName(individualCustomerDto.getLastName())
                .birthDate(individualCustomerDto.getBirthDate())
                .additionalServiceDtos(additionalServiceDtos)
                .total(total)
                .build();

        return new SuccessDataResult<InvoiceIndividualCustomerDto>(invoiceDto);
    }

    @Override
    public DataResult<InvoiceCorporateCustomerDto> getInvoiceOfCorporateCustomer(Integer rentalId) {
        List<PaymentDto> paymentDtos=this.paymentService.getByRentalId(rentalId).getData();
        List<AdditionalServiceDto> additionalServiceDtos = this.additionalServiceService.getByRentalId(rentalId).getData();
        RentalDto rentalDto = this.rentalService.getById(rentalId).getData();
        CorporateCustomerDto corporateCustomerDto=this.corporateCustomerService.getById(rentalDto.getCustomerId()).getData();


        Double total = paymentDtos.stream()
                .map(a->a.getTotal())
                .reduce((double) 0, (Double::sum));

        total = additionalServiceDtos.stream()
                .map(a->a.getPrice())
                .reduce((double) total, (Double::sum));

        InvoiceCorporateCustomerDto invoiceDto=InvoiceCorporateCustomerDto.builder()
                .companyName(corporateCustomerDto.getCompanyName())
                .taxNumber(corporateCustomerDto.getTaxNumber())
                .additionalServiceDtos(additionalServiceDtos)
                .total(total)
                .build();

        return new SuccessDataResult<InvoiceCorporateCustomerDto>(invoiceDto);
    }
}
