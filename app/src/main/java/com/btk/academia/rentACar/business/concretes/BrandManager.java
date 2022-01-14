package com.btk.academia.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.BrandService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.dtos.BrandDto;
import com.btk.academia.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btk.academia.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.ErrorResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.BrandDao;
import com.btk.academia.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao,ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<BrandDto>> getAll() {
		List<Brand> brandList = this.brandDao.findAll();
		List<BrandDto> response = brandList.stream()
				.map(brand -> modelMapperService.forDto().map(brand, BrandDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<BrandDto>>(response);
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {

		Result result = BusinessRules.run(checkIfBrandNameExists(createBrandRequest.getName()), checkIfBrandLimitExceeded(3));
		
		if(!result.isSuccess()) {
			return result;
		}

		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.brandAdded);
	}
	
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.brandUpdated);
	}

	private Result checkIfBrandNameExists(String brandName) {
		Brand brand = brandDao.findByName(brandName);
		if (brand != null) {
			return new ErrorResult(Messages.brandNameAlreadyExists);	
		}
		
		return new SuccessResult();
	}

	private Result checkIfBrandLimitExceeded(Integer limit) {
		if (this.brandDao.count() >= limit) {
			return new ErrorResult(Messages.brandLimitExceeded);
		}

		return new SuccessResult();
	}

}
