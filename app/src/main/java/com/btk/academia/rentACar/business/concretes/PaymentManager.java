package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.PaymentService;
import com.btk.academia.rentACar.business.abstracts.RentalService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.dtos.RentalListDto;
import com.btk.academia.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btk.academia.rentACar.dataAccess.abstracts.PaymentDao;
import com.btk.academia.rentACar.entities.concretes.AdditionalService;
import com.btk.academia.rentACar.entities.concretes.Car;
import com.btk.academia.rentACar.entities.concretes.Payment;
import com.btk.academia.rentACar.entities.concretes.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private AdditionalServiceManager additionalServiceManager;

    @Autowired
    public PaymentManager(PaymentDao paymentDao,ModelMapperService modelMapperService
    ,RentalService rentalService, AdditionalServiceManager additionalServiceManager) {
        this.paymentDao=paymentDao;
        this.modelMapperService=modelMapperService;
        this.rentalService=rentalService;
        this.additionalServiceManager=additionalServiceManager;
    }

    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) {
        Result result = BusinessRules.run(

        );

        if(!result.isSuccess()) {
            return result;
        }

        Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

        Integer rentalId =createPaymentRequest.getRentalId();

        RentalListDto rental= rentalService.getById(rentalId).getData();

        Period diff = Period.between(rental.getRentDate(), rental.getReturnDate());

        Car car = rentalService.getByCarId(rental.getCarId()).getData().getCar();
        AdditionalService additionalService = additionalServiceManager.getByRentalId(rentalId).getData();

        Double total = car.getDailyPrice()*diff.getDays()+additionalService.getPrice();
        
        payment.setTotal(total);

        this.paymentDao.save(payment);
        return new SuccessResult("ödeme alındı");
    }
}
