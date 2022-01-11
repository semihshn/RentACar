package com.btk.academia.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.CarService;
import com.btk.academia.rentACar.business.dtos.CarListDto;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.dataAccess.abstracts.CarDao;
import com.btk.academia.rentACar.entities.concretes.Car;

@Service

public class CarManager implements CarService {
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarDao carDao) {
		this.carDao=carDao;
	}

	@Override
	public DataResult<List<CarListDto>> getAll() {
		List<Car> carList = this.carDao.findAll();
		List<CarListDto> response = carList.stream()
				.map(car->modelMapperService.forDto()
						.map(car, CarListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response);
	}

}
