package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.requests.additionalServiceRequest.CreateAdditionalServiceRequest;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);

}
