package com.btk.academia.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.CarDamageService;
import com.btk.academia.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cardamage")
public class CarDamagesController {
	
	private CarDamageService carDamageService;

	public CarDamagesController(CarDamageService carDamageService) {
		this.carDamageService = carDamageService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest){
		return this.carDamageService.add(createCarDamageRequest);
	}

}
