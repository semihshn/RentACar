package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.*;
import com.btk.academia.rentACar.business.dtos.CarDto;
import com.btk.academia.rentACar.business.dtos.RentalDto;
import com.btk.academia.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btk.academia.rentACar.core.adapters.CustomerCheckLimitService;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.ErrorResult;
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
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private CarService carService;
    private AdditionalServiceService additionalServiceService;
    private CustomerCheckLimitService customerCheckLimitService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao,ModelMapperService modelMapperService
    ,RentalService rentalService, AdditionalServiceService additionalServiceService
    ,CarService carService, CustomerCheckLimitService customerCheckLimitService
    ) {
        this.paymentDao=paymentDao;
        this.modelMapperService=modelMapperService;
        this.rentalService=rentalService;
        this.additionalServiceService=additionalServiceService;
        this.carService=carService;
        this.customerCheckLimitService=customerCheckLimitService;
    }

    @Override
    public Result add(CreatePaymentRequest createPaymentRequest) {
        Result result = BusinessRules.run(
                customerCheckLimitService.checkIfLimitIsEnought()
        );

        if(!result.isSuccess()) {
            return result;
        }

            Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

            Integer rentalId = createPaymentRequest.getRentalId();
            payment = paymentCalculate(payment, rentalId);

            this.paymentDao.save(payment);
            return new SuccessResult("ödeme alındı");
    }

    private Payment paymentCalculate(Payment payment,Integer rentalId){

        RentalDto rental= rentalService.getById(rentalId).getData();

        //date difference
        long diff= ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate());

        //dependency
        CarDto carDto = carService.getById(rental.getCarId()).getData();
        List<AdditionalService> additionalService = additionalServiceService.getByRentalId(rentalId).getData();

        Double serviceTotalPrice = additionalService.stream()
                .map(a->a.getPrice())
                .reduce((double) 0, (Double::sum));

        Double total = carDto.getDailyPrice()*diff+serviceTotalPrice;

        if(!rental.getReturnDate().equals(LocalDateTime.now())){
            payment.setTotal(total);
        }

        payment.setPaymentDate(LocalDateTime.now());

        return payment;

    }


}
