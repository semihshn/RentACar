package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface CarDamageService {
	
	Result add(CreateCarDamageRequest createCarDamageRequest);

}
