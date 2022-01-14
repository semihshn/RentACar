package com.btk.academia.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.BrandService;
import com.btk.academia.rentACar.business.dtos.BrandDto;
import com.btk.academia.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btk.academia.rentACar.business.requests.brandRequests.UpdateBrandRequest;
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
	public DataResult<List<BrandDto>> getAll(){
		return this.brandService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest){
		return this.brandService.add(createBrandRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest){
		return this.brandService.update(updateBrandRequest);
	}

}
