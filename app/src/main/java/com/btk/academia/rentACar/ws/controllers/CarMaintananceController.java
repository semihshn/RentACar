package com.btk.academia.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.CarMaintanceService;
import com.btk.academia.rentACar.business.dtos.CarMaintanceDto;
import com.btk.academia.rentACar.business.requests.carMaintanceRequest.CreateCarMaintanceRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carmaintanance")
public class CarMaintananceController {
	
	private CarMaintanceService carMaintanceService;

	public CarMaintananceController(CarMaintanceService carMaintanceService) {
		this.carMaintanceService = carMaintanceService;
	}
	
	@GetMapping("getall")
	public DataResult<List<CarMaintanceDto>> getAll(){
		return this.carMaintanceService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarMaintanceRequest createCarMaintanceRequest){
		return this.carMaintanceService.add(createCarMaintanceRequest);
	}

}
