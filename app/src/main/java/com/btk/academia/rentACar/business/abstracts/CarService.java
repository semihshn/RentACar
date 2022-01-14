package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.CarDto;
import com.btk.academia.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btk.academia.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface CarService {
	
	DataResult<List<CarDto>> getAll();
	Result add(CreateCarRequest createCarRequest);
	Result update(UpdateCarRequest updateCarRequest);

}
