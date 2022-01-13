package com.btk.academia.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.CityService;
import com.btk.academia.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/citys")
public class CitysController {
	
	private CityService cityService;

	public CitysController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCityRequest createCityRequest){
		return this.cityService.add(createCityRequest);
	}

}
