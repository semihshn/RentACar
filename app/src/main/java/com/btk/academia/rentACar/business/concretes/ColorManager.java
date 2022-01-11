package com.btk.academia.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.ColorService;
import com.btk.academia.rentACar.business.dtos.ColorListDto;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.dataAccess.abstracts.ColorDao;
import com.btk.academia.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{
	
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao) {
		this.colorDao=colorDao;
	}

	@Override
	public DataResult<List<ColorListDto>> getAll() {
		List<Color> colorList = this.colorDao.findAll();
		List<ColorListDto> response = colorList.stream()
				.map(color->modelMapperService.forDto()
						.map(color, ColorListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ColorListDto>>(response);
	}

}
