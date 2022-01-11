package com.btk.academia.rentACar.ws.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btk.academia.rentACar.business.abstracts.ColorService;
import com.btk.academia.rentACar.business.dtos.ColorListDto;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
	
	private ColorService colorService;

	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("getall")
	public List<ColorListDto> getAll(){
		return this.colorService.getAll();
	}

}
