package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.ColorListDto;
import com.btk.academia.rentACar.core.utilities.results.DataResult;

public interface ColorService {
	
	DataResult<List<ColorListDto>> getAll();

}
