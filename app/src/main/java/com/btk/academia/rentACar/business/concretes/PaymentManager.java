package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.AdditionalServiceService;
import com.btk.academia.rentACar.business.abstracts.PaymentService;
import com.btk.academia.rentACar.business.abstracts.RentalService;
import com.btk.academia.rentACar.business.dtos.RentalDto;
import com.btk.academia.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.PaymentDao;
import com.btk.academia.rentACar.entities.concretes.AdditionalService;
import com.btk.academia.rentACar.entities.concretes.Car;
import com.btk.academia.rentACar.entities.concretes.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private AdditionalServiceService additionalServiceService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao,ModelMapperService modelMapperService
    ,RentalService rentalService, AdditionalServiceService additionalServiceService) {
        this.paymentDao=paymentDao;
        this.modelMapperService=modelMapperService;
        this.rentalService=rentalService;
        this.additionalServiceService=additionalServiceService;
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
        payment=paymentCalculate(payment,rentalId);

        this.paymentDao.save(payment);
        return new SuccessResult("ödeme alındı");
    }

    private Payment paymentCalculate(Payment payment,Integer rentalId){

        RentalDto rental= rentalService.getById(rentalId).getData();

        //date difference
        Period diff = Period.between(rental.getRentDate(), rental.getReturnDate());

        //dependency
        Car car = rentalService.getByCarId(rental.getCarId()).getData().getCar();
        AdditionalService additionalService = additionalServiceService.getByRentalId(rentalId).getData();

        Double total = car.getDailyPrice()*diff.getDays()+additionalService.getPrice();

        if(!rental.getReturnDate().equals(LocalDateTime.now())){
            payment.setTotal(total);
        }

        return payment;

    }
}
