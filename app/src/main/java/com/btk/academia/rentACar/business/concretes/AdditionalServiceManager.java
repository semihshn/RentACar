package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.entities.concretes.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.AdditionalServiceService;
import com.btk.academia.rentACar.business.requests.additionalServiceRequest.CreateAdditionalServiceRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btk.academia.rentACar.entities.concretes.AdditionalService;

import java.util.List;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao,ModelMapperService modelMapperService) {
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		Result result = BusinessRules.run();
		
		if(!result.isSuccess()) {
			return result;
		}

		AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult("Ek hizmet eklendi");
	}

	@Override
	public DataResult<List<AdditionalService>> getByRentalId(Integer rentalId) {
		List<AdditionalService> additionalService = this.additionalServiceDao.findByRentalId(rentalId);
		return new SuccessDataResult<List<AdditionalService>>(additionalService);
	}


}
