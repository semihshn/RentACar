package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.CustomerDto;
import com.btk.academia.rentACar.core.utilities.results.DataResult;

public interface CustomerService {
	
	DataResult<CustomerDto> getById(Integer customerId);

}
