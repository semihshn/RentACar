package com.btk.academia.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.CarService;
import com.btk.academia.rentACar.business.dtos.CarListDto;
import com.btk.academia.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;

	public CarsController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping("getall")
	public DataResult<List<CarListDto>> getAll(){
		return this.carService.getAll();
	}

}
