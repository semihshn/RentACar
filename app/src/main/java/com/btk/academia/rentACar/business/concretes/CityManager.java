package com.btk.academia.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.CityService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btk.academia.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.CityDao;
import com.btk.academia.rentACar.entities.concretes.City;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao,ModelMapperService modelMapperService) {
		this.cityDao=cityDao;
		this.modelMapperService=modelMapperService;
	}
	
	@Override
	public Result add(CreateCityRequest createCityRequest) {
		Result result = BusinessRules.run();
		
		if(!result.isSuccess()) {
			return result;
		}

		City city = modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccessResult("Şehir eklendi");
	}

}
