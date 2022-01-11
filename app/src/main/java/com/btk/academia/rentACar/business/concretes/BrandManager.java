package com.btk.academia.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.BrandService;
import com.btk.academia.rentACar.business.dtos.BrandListDto;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.dataAccess.abstracts.BrandDao;
import com.btk.academia.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandDao brandDao) {
		this.brandDao=brandDao;
	}

	@Override
	public List<BrandListDto> getAll() {
		List<Brand> brandList = this.brandDao.findAll();
		List<BrandListDto> response = brandList.stream()
				.map(brand->modelMapperService.forDto()
						.map(brand, BrandListDto.class))
				.collect(Collectors.toList());
		
		return response;
	}

}
