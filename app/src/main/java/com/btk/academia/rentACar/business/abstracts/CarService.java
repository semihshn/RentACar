package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.CarListDto;

public interface CarService {
	
	List<CarListDto> getAll();

}
