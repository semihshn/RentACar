package com.btk.academia.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.CarService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.dtos.CarDto;
import com.btk.academia.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btk.academia.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.CarDao;
import com.btk.academia.rentACar.entities.concretes.Car;

@Service

public class CarManager implements CarService {
	
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarDao carDao,ModelMapperService modelMapperService) {
		this.carDao=carDao;
		this.modelMapperService=modelMapperService;
	}

	@Override
	public DataResult<List<CarDto>> getAll() {
		List<Car> carList = this.carDao.findAll();
		List<CarDto> response = carList.stream()
				.map(car->modelMapperService.forDto()
						.map(car, CarDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarDto>>(response);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Result result = BusinessRules.run();
		
		if(!result.isSuccess()) {
			return result;
		}

		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(Messages.carAdded);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(Messages.carUpdated);
	}

}
