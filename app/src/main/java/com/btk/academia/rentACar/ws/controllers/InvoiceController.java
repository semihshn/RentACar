package com.btk.academia.rentACar.ws.controllers;

import com.btk.academia.rentACar.business.abstracts.InvoiceService;
import com.btk.academia.rentACar.business.abstracts.PaymentService;
import com.btk.academia.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("get/individual")
    public Result getIndividual(@RequestParam Integer rentalId){
        return this.invoiceService.getInvoiceOfIndividualCustomer(rentalId);
    }

    @GetMapping("get/corporate")
    public Result getCorporate(@RequestParam Integer rentalId){
        return this.invoiceService.getInvoiceOfCorporateCustomer(rentalId);
    }
}
