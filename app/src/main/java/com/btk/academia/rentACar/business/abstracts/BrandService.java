package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.BrandListDto;


public interface BrandService {
	
	List<BrandListDto> getAll();

}
