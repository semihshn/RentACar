package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.CarMaintanceDto;
import com.btk.academia.rentACar.business.requests.carMaintanceRequest.CreateCarMaintanceRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.CarMaintanance;

public interface CarMaintanceService {
	
	DataResult<List<CarMaintanceDto>> getAll();
	Result add(CreateCarMaintanceRequest createCarMaintanceRequest);
	DataResult<CarMaintanance> getByCarId(Integer id);

}
