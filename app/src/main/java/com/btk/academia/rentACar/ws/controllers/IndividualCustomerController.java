package com.btk.academia.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.IndividualCustomerService;
import com.btk.academia.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomerController {
	
	private IndividualCustomerService individualCustomerService;

	public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
		this.individualCustomerService = individualCustomerService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest){
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

}
