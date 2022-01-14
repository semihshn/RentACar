package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.RentalDto;
import com.btk.academia.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.Rental;

public interface RentalService {
	
	Result add(CreateRentalRequest createRentalRequest);
	DataResult<RentalDto> getById(Integer id);
	DataResult<Rental> getByCarId(Integer id);
	DataResult<List<RentalDto>> getAll(Integer pageNo, Integer pageSize);

}
