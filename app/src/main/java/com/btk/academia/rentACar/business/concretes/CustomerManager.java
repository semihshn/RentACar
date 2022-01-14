package com.btk.academia.rentACar.business.concretes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.CustomerService;
import com.btk.academia.rentACar.business.dtos.CustomerDto;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.dataAccess.abstracts.CustomerDao;
import com.btk.academia.rentACar.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {
	
	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao,ModelMapperService modelMapperService) {
		this.customerDao=customerDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public DataResult<CustomerDto> getById(Integer id) {
		Customer customer = this.customerDao.findById(id).get();
		CustomerDto response = modelMapperService.forDto().map(customer, CustomerDto.class);

		return new SuccessDataResult<CustomerDto>(response);
	}

}
