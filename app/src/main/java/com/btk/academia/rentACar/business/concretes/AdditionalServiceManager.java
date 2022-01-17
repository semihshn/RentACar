package com.btk.academia.rentACar.business.concretes;

import com.btk.academia.rentACar.business.dtos.AdditionalServiceDto;
import com.btk.academia.rentACar.business.dtos.BrandDto;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
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
import java.util.stream.Collectors;

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
		additionalService.setId(0);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult("Ek hizmet eklendi");
	}

	@Override
	public DataResult<List<AdditionalServiceDto>> getByRentalId(Integer rentalId) {
		List<AdditionalService> additionalServices = this.additionalServiceDao.findByRentalId(rentalId);

		List<AdditionalServiceDto> response = additionalServices.stream()
				.map(additionalService ->
						modelMapperService.forDto().map(additionalService, AdditionalServiceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalServiceDto>>(response);
	}

	@Override
	public DataResult<List<AdditionalServiceDto>> getAll() {
		List<AdditionalService> additionalServiceList = this.additionalServiceDao.findAll();
		List<AdditionalServiceDto> response = additionalServiceList.stream()
				.map(additionalService -> modelMapperService.forDto().map(additionalService, AdditionalServiceDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalServiceDto>>(response);
	}


}
