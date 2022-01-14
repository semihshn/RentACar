package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btk.academia.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.Rental;

public interface PaymentService {

    Result add(CreatePaymentRequest createPaymentRequest);
}
