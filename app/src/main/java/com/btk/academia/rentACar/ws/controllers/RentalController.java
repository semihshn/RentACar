package com.btk.academia.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.RentalService;
import com.btk.academia.rentACar.business.dtos.RentalDto;
import com.btk.academia.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	
	private RentalService rentalService;

	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	@PostMapping("add/individual")
	public Result addForIndividual(@RequestBody CreateRentalRequest createRentalRequest){
		return this.rentalService.addForIndividual(createRentalRequest);
	}

	@PostMapping("add/corporate")
	public Result addForCorporate(@RequestBody CreateRentalRequest createRentalRequest){
		return this.rentalService.addForCorporate(createRentalRequest);
	}
	
	@GetMapping("getall")
	public DataResult<List<RentalDto>> getAll(@RequestParam Integer pageNo, @RequestParam(required = false) Integer pageSize){
		return this.rentalService.getAll(pageNo,pageSize);
	}
	

}
