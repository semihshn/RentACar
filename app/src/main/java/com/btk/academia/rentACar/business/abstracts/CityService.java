package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface CityService {

	Result add(CreateCityRequest createCityRequest);
	
}
