package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.abstracts.*;
import com.btk.academia.rentACar.business.dtos.AdditionalServiceDto;
import com.btk.academia.rentACar.business.dtos.CarDto;
import com.btk.academia.rentACar.business.dtos.PaymentDto;
import com.btk.academia.rentACar.business.dtos.RentalDto;
import com.btk.academia.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btk.academia.rentACar.business.requests.userInfoRequests.CreateUserInfoRequest;
import com.btk.academia.rentACar.core.adapters.CustomerCheckLimitService;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.*;
import com.btk.academia.rentACar.dataAccess.abstracts.PaymentDao;
import com.btk.academia.rentACar.entities.concretes.*;
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
import java.util.stream.Collectors;

@Service
public class PaymentManager implements PaymentService {

    private PaymentDao paymentDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private CarService carService;
    private AdditionalServiceService additionalServiceService;
    private CustomerCheckLimitService customerCheckLimitService;
    private UserInfoService userInfoService;
    private PromationCodeService promationCodeService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao,ModelMapperService modelMapperService
    ,RentalService rentalService, AdditionalServiceService additionalServiceService
    ,CarService carService, CustomerCheckLimitService customerCheckLimitService,
                          UserInfoService userInfoService, PromationCodeService promationCodeService
    ) {
        this.paymentDao=paymentDao;
        this.modelMapperService=modelMapperService;
        this.rentalService=rentalService;
        this.additionalServiceService=additionalServiceService;
        this.carService=carService;
        this.customerCheckLimitService=customerCheckLimitService;
        this.userInfoService=userInfoService;
        this.promationCodeService=promationCodeService;
    }

    @Override
    public Result add(CreatePaymentRequest createPaymentRequest, Boolean isSaveUserInfo, String code) {
        Result result = BusinessRules.run(
                customerCheckLimitService.checkIfLimitIsEnought()
        );

        if(!result.isSuccess()) {
            return result;
        }

            Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

            Integer rentalId = createPaymentRequest.getRentalId();
            payment = paymentCalculate(payment, rentalId);

            RentalDto rental= rentalService.getById(rentalId).getData();

            if(isSaveUserInfo){
                CreateUserInfoRequest request = CreateUserInfoRequest.builder()
                        .name(createPaymentRequest.getName())
                        .cardNumber(createPaymentRequest.getCardNumber())
                        .customerId(rental.getCustomerId())
                        .build();

                this.userInfoService.add(request);
            }

            if(checkIfPromotionCode(code).isSuccess()){
                PromationCode promationCode=promationCodeService.getByPromationCode(code).getData();
                Double total=payment.getTotal()-(payment.getTotal()*promationCode.getDiscountRate());
                payment.setTotal(total);
            }

            payment.setId(0);
            this.paymentDao.save(payment);
            return new SuccessResult("ödeme alındı");
    }

    @Override
    public DataResult<List<PaymentDto>> getByRentalId(Integer rentalId) {
        List<Payment> paymentList = this.paymentDao.findByRentalId(rentalId);

        List<PaymentDto> response = paymentList.stream()
                .map(payment->modelMapperService.forDto()
                        .map(payment, PaymentDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<PaymentDto>>(response);
    }

    private Payment paymentCalculate(Payment payment,Integer rentalId){

        RentalDto rental= rentalService.getById(rentalId).getData();

        //date difference
        long diff= ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate());

        //dependency
        CarDto carDto = carService.getById(rental.getCarId()).getData();
        List<AdditionalServiceDto> additionalService = additionalServiceService.getByRentalId(rentalId).getData();

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

    private Result checkIfPromotionCode(String code) {
        PromationCode promationCode=promationCodeService.getByPromationCode(code).getData();
        if (!(promationCode==null?"":promationCode).equals(promationCode.getPromationCode())) {
            return new ErrorResult();
        }

        return new SuccessResult();
    }

}
