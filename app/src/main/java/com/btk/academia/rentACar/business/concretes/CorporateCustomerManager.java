package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.dtos.CorporateCustomerDto;
import com.btk.academia.rentACar.business.dtos.IndividualCustomerDto;
import com.btk.academia.rentACar.core.adapters.CustomerCheckFindeksScore;
import com.btk.academia.rentACar.core.utilities.results.*;
import com.btk.academia.rentACar.entities.concretes.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.CorporateCustomerService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btk.academia.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
	
	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	private CustomerCheckFindeksScore customerCheckFindeksScore;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao,
									ModelMapperService modelMapperService,
									CustomerCheckFindeksScore customerCheckFindeksScore) {
		this.corporateCustomerDao=corporateCustomerDao;
		this.modelMapperService=modelMapperService;
		this.customerCheckFindeksScore=customerCheckFindeksScore;
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		Result result = BusinessRules.run(
				checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName())
				);
		
		if(!result.isSuccess()) {
			return result;
		}

		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomer.setId(0);
		corporateCustomer.setFindeksScore(customerCheckFindeksScore.getCorporateCustomerFindeksScore(createCorporateCustomerRequest.getVergiNo()));
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerAdded);
	}

	@Override
	public DataResult<CorporateCustomerDto> getById(Integer customerId) {
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.findById(customerId).get();
		CorporateCustomerDto response = modelMapperService.forDto().map(corporateCustomer, CorporateCustomerDto.class);

		return new SuccessDataResult<CorporateCustomerDto>(response);
	}

	private Result checkIfCompanyNameExists(String companyName) {
		if (this.corporateCustomerDao.findByCompanyName(companyName)!=null) {
			return new ErrorResult(Messages.corporateCompanyNameExists);
		}

		return new SuccessResult();
	}

}
