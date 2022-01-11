package com.btk.academia.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.BrandService;
import com.btk.academia.rentACar.business.dtos.BrandListDto;
import com.btk.academia.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;

	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping("getall")
	public DataResult<List<BrandListDto>> getAll(){
		return this.brandService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest){
		return this.brandService.add(createBrandRequest);
	}

}
