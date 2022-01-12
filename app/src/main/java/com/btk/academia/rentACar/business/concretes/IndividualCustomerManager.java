package com.btk.academia.rentACar.business.concretes;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.IndividualCustomerService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.ErrorResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btk.academia.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,ModelMapperService modelMapperService) {
		this.individualCustomerDao=individualCustomerDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		
		Result result = BusinessRules.run(
				checkIfEmailExists(createIndividualCustomerRequest.getEmail())
				,checkIfBirthDateLimit(createIndividualCustomerRequest.getBirthDate().getYear())
				);
		
		if(!result.isSuccess()) {
			return result;
		}

		IndividualCustomer individualCustomer = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.individualCustomerAdded);
	}
	
	private Result checkIfEmailExists(String email) {
		if (this.individualCustomerDao.findByEmail(email)!=null) {
			return new ErrorResult(Messages.brandLimitExceeded);
		}

		return new SuccessResult();
	}
	
	private Result checkIfBirthDateLimit(Integer birtYear) {

        Integer year=Calendar.getInstance().get(Calendar.YEAR);
        
		if (birtYear<year+18) {
			return new ErrorResult(Messages.brandLimitExceeded);
		}

		return new SuccessResult();
	}

}
