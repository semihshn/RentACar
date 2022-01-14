package com.btk.academia.rentACar.ws.controllers;

import com.btk.academia.rentACar.business.abstracts.IndividualCustomerService;
import com.btk.academia.rentACar.business.abstracts.PaymentService;
import com.btk.academia.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btk.academia.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreatePaymentRequest createPaymentRequest){
        return this.paymentService.add(createPaymentRequest);
    }
}
