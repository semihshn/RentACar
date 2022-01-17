package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.AdditionalServiceService;
import com.btk.academia.rentACar.business.abstracts.InvoiceService;
import com.btk.academia.rentACar.business.abstracts.PaymentService;
import com.btk.academia.rentACar.business.abstracts.RentalService;
import com.btk.academia.rentACar.business.dtos.AdditionalServiceDto;
import com.btk.academia.rentACar.business.dtos.InvoiceDto;
import com.btk.academia.rentACar.business.dtos.PaymentDto;
import com.btk.academia.rentACar.business.dtos.RentalDto;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.entities.concretes.AdditionalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceManager implements InvoiceService {

    private ModelMapperService modelMapperService;
    private AdditionalServiceService additionalServiceService;
    private PaymentService paymentService;

    public InvoiceManager(ModelMapperService modelMapperService,
                          AdditionalServiceService additionalServiceService,
                          PaymentService paymentService) {
        this.modelMapperService = modelMapperService;
        this.additionalServiceService=additionalServiceService;
        this.paymentService=paymentService;
    }

    @Override
    public DataResult<InvoiceDto> get(Integer rentalId) {
        List<PaymentDto> paymentDtos=this.paymentService.getByRentalId(rentalId).getData();
        List<AdditionalServiceDto> additionalServiceDtos = this.additionalServiceService.getByRentalId(rentalId).getData();

        Double total = paymentDtos.stream()
                .map(a->a.getTotal())
                .reduce((double) 0, (Double::sum));

        total = additionalServiceDtos.stream()
                .map(a->a.getPrice())
                .reduce((double) total, (Double::sum));

        InvoiceDto invoiceDto=InvoiceDto.builder()
                .additionalServiceDtos(additionalServiceDtos)
                .total(total)
                .build();

        return new SuccessDataResult<InvoiceDto>(invoiceDto);
    }
}
