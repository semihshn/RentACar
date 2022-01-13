package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.ColorListDto;
import com.btk.academia.rentACar.business.dtos.CustomerListDto;
import com.btk.academia.rentACar.business.dtos.RentalListDto;
import com.btk.academia.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.Car;
import com.btk.academia.rentACar.entities.concretes.Rental;

public interface RentalService {
	
	Result add(CreateRentalRequest createRentalRequest);
	DataResult<RentalListDto> getById(Integer id);
	DataResult<Rental> getByCarId(Integer id);
	DataResult<List<RentalListDto>> getAll();

}
