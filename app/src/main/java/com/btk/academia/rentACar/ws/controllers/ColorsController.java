package com.btk.academia.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.ColorService;
import com.btk.academia.rentACar.business.dtos.ColorDto;
import com.btk.academia.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btk.academia.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
	
	private ColorService colorService;

	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("getall")
	public DataResult<List<ColorDto>> getAll(){
		return this.colorService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CreateColorRequest createColorRequest){
		return this.colorService.add(createColorRequest);
	}
	
	@PutMapping("update")
	public Result update(@RequestBody UpdateColorRequest updateColorRequest){
		return this.colorService.update(updateColorRequest);
	}

}
