package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.CustomerDto;
import com.btk.academia.rentACar.business.dtos.IndividualCustomerDto;
import com.btk.academia.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	DataResult<IndividualCustomerDto> getById(Integer customerId);

}
