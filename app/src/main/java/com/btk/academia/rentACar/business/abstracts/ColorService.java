package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.ColorListDto;

public interface ColorService {
	
	List<ColorListDto> getAll();

}
