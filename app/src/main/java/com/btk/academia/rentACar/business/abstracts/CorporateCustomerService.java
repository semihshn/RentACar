package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.CorporateCustomerDto;
import com.btk.academia.rentACar.business.dtos.IndividualCustomerDto;
import com.btk.academia.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {
	
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	DataResult<CorporateCustomerDto> getById(Integer customerId);

}
