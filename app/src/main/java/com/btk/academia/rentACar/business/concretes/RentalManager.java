package com.btk.academia.rentACar.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import com.btk.academia.rentACar.business.abstracts.CarService;
import com.btk.academia.rentACar.business.dtos.CarDto;
import com.btk.academia.rentACar.business.dtos.CustomerDto;
import com.btk.academia.rentACar.entities.concretes.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.CarMaintanceService;
import com.btk.academia.rentACar.business.abstracts.CustomerService;
import com.btk.academia.rentACar.business.abstracts.RentalService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.dtos.RentalDto;
import com.btk.academia.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.ErrorResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.RentalDao;
import com.btk.academia.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private CarMaintanceService carMaintanceService;
	private CarService carService;

	@Autowired
	public RentalManager(RentalDao rentalDao,
						 ModelMapperService modelMapperService,
						 CustomerService customerService,
						 @Lazy CarMaintanceService carMaintanceService,
						 CarService carService) {
		this.rentalDao=rentalDao;
		this.modelMapperService=modelMapperService;
		this.customerService=customerService;
		this.carMaintanceService=carMaintanceService;
		this.carService=carService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(
				checkCarExistRentalHistory(createRentalRequest.getRentDate().getYear(),createRentalRequest.getReturnDate().getYear()),
				checkCarKilometer(createRentalRequest.getRentedKilometer(),createRentalRequest.getReturnedKilometer()),
				checkIfCustomerExists(createRentalRequest.getCustomerId()),
				checkIfCustomerFindeksScore(createRentalRequest.getCustomerId(),createRentalRequest.getCarId())
				);
		
		if(!result.isSuccess()) {
			return result;
		}

		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setId(0);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalAdded);
	}
	
	@Override
	public DataResult<RentalDto> getById(Integer id) {
		Rental rental = this.rentalDao.findById(id).get();
		RentalDto response = modelMapperService.forDto().map(rental, RentalDto.class);

		return new SuccessDataResult<RentalDto>(response);
	}
	
	@Override
	public DataResult<Rental> getByCarId(Integer carId) {
		Rental rental = this.rentalDao.findByCarId(carId);
		return new SuccessDataResult<Rental>(rental);
	}
	
	@Override
	public DataResult<List<RentalDto>> getAll(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize==null?10:pageSize);
		
		List<Rental> rentalList = this.rentalDao.findAll(pageable).getContent();
		List<RentalDto> response = rentalList.stream()
				.map(rental->modelMapperService.forDto()
						.map(rental, RentalDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<RentalDto>>(response);
	}
	
	private Result checkCarExistRentalHistory(Integer rentDate, Integer returnDate) {
        
		if (rentDate<returnDate) {
			return new ErrorResult(Messages.illogicalRentDate);
		}

		return new SuccessResult();
	}
	
	private Result checkCarKilometer(Integer rentedKilometer, Integer returnedKilometer) {
        
		if (rentedKilometer>returnedKilometer) {
			return new ErrorResult(Messages.illogicalRentKilometer);
		}

		return new SuccessResult();
	}
	
	private Result checkIfCustomerExists(Integer customerId) {
        
		if (customerService.getById(customerId)==null) {
			return new ErrorResult(Messages.notAvailableCustomer);
		}

		return new SuccessResult();
	}

	private Result checkIfCustomerFindeksScore(Integer customerId, Integer carId) {

		DataResult<CarDto> car=carService.getById(carId);
		DataResult<CustomerDto> customer=customerService.getById(customerId);

		if(car.getData().getFindexScore()>customer.getData().getFindeksScore()){
			System.out.println(car.getData().getFindexScore()+" - "+customer.getData().getFindeksScore());
			return new ErrorResult("Arabayı kiralayabilmek için findeks score unuz yeterli değil");
		}

		return new SuccessResult();
	}
	
	//Araba bakımdaysa kiraya gönderilemez
	//carmaintance tablosunda, carId kontrol et ve bakımda olup olmadığını öğren
	private Result checkCarMaintanance(Integer carId) {
        
		if (carMaintanceService.getByCarId(carId)!=null) {
			return new ErrorResult("bu araba bakımda kiralanamaz");
		}

		return new SuccessResult();
	}

}
