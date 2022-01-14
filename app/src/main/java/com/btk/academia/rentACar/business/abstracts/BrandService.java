package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.BrandDto;
import com.btk.academia.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btk.academia.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;


public interface BrandService {
	
	DataResult<List<BrandDto>> getAll();
	Result add(CreateBrandRequest createBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
	

}
