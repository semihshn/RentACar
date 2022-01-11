package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.CarListDto;
import com.btk.academia.rentACar.core.utilities.results.DataResult;

public interface CarService {
	
	DataResult<List<CarListDto>> getAll();

}
