package com.btk.academia.rentACar.business.abstracts;

import com.btk.academia.rentACar.business.dtos.CustomerListDto;
import com.btk.academia.rentACar.core.utilities.results.DataResult;

public interface CustomerService {
	
	DataResult<CustomerListDto> getById(Integer id);

}
