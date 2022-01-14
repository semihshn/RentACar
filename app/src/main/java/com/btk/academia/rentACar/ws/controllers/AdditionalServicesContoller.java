package com.btk.academia.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.AdditionalServiceService;
import com.btk.academia.rentACar.business.requests.additionalServiceRequest.CreateAdditionalServiceRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesContoller {
	
	private AdditionalServiceService additionalServiceService;

	public AdditionalServicesContoller(AdditionalServiceService additionalServiceService) {
		this.additionalServiceService = additionalServiceService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreateAdditionalServiceRequest createAdditionalServiceRequest){
		return this.additionalServiceService.add(createAdditionalServiceRequest);
	}

}
