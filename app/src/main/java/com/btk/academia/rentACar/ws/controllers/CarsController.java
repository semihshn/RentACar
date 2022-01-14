package com.btk.academia.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.CarService;
import com.btk.academia.rentACar.business.dtos.CarListDto;
import com.btk.academia.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btk.academia.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

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
	
	@PostMapping("add")
	public Result add(@RequestBody CreateCarRequest createCarRequest){
		return this.carService.add(createCarRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest){
		return this.carService.update(updateCarRequest);
	}

}
