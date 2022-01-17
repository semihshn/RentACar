package com.btk.academia.rentACar.business.concretes;

import java.util.Calendar;

import com.btk.academia.rentACar.core.adapters.CustomerCheckFindeksScore;
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
	private CustomerCheckFindeksScore customerCheckFindeksScore;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
									 ModelMapperService modelMapperService,
									 CustomerCheckFindeksScore customerCheckFindeksScore) {
		this.individualCustomerDao=individualCustomerDao;
		this.modelMapperService=modelMapperService;
		this.customerCheckFindeksScore=customerCheckFindeksScore;
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

		individualCustomer.setId(0);
		individualCustomer.setFindeksScore(customerCheckFindeksScore.getIndividualCustomerFindeksScore(createIndividualCustomerRequest.getTc()));
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.individualCustomerAdded);
	}
	
	private Result checkIfEmailExists(String email) {
		if (this.individualCustomerDao.findByEmail(email)!=null) {
			return new ErrorResult("Bu email ile kayıtlı birisi var");
		}

		return new SuccessResult();
	}
	
	private Result checkIfBirthDateLimit(Integer birtYear) {

        Integer year=Calendar.getInstance().get(Calendar.YEAR);
        
		if (birtYear>year-18) {
			return new ErrorResult("18 yaşından küçükler kayıt olamaz");
		}

		return new SuccessResult();
	}

}
