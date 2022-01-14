package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.requests.additionalServiceRequest.CreateAdditionalServiceRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.AdditionalService;
import com.btk.academia.rentACar.entities.concretes.Rental;

public interface AdditionalServiceService {
	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	DataResult<AdditionalService> getByRentalId(Integer rentalId);

}
