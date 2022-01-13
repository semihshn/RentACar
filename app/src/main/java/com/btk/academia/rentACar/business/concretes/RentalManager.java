package com.btk.academia.rentACar.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btk.academia.rentACar.business.abstracts.CustomerService;
import com.btk.academia.rentACar.business.abstracts.RentalService;
import com.btk.academia.rentACar.business.constants.Messages;
import com.btk.academia.rentACar.business.dtos.ColorListDto;
import com.btk.academia.rentACar.business.dtos.CustomerListDto;
import com.btk.academia.rentACar.business.dtos.RentalListDto;
import com.btk.academia.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btk.academia.rentACar.core.utilities.business.BusinessRules;
import com.btk.academia.rentACar.core.utilities.mapping.ModelMapperService;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.ErrorResult;
import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessDataResult;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;
import com.btk.academia.rentACar.dataAccess.abstracts.RentalDao;
import com.btk.academia.rentACar.entities.concretes.Car;
import com.btk.academia.rentACar.entities.concretes.Color;
import com.btk.academia.rentACar.entities.concretes.Customer;
import com.btk.academia.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	
	@Autowired
	public RentalManager(RentalDao rentalDao,ModelMapperService modelMapperService,CustomerService customerService) {
		this.rentalDao=rentalDao;
		this.modelMapperService=modelMapperService;
		this.customerService=customerService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(
				checkCarExistRentalHistory(createRentalRequest.getRentDate().getYear(),createRentalRequest.getReturnDate().getYear()),
				checkCarKilometer(createRentalRequest.getRentedKilometer(),createRentalRequest.getReturnedKilometer()),
				checkIfCustomerExists(createRentalRequest.getCustomerId())
				);
		
		if(!result.isSuccess()) {
			return result;
		}

		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalAdded);
	}
	
	@Override
	public DataResult<RentalListDto> getById(Integer id) {
		Rental rental = this.rentalDao.findById(id).get();
		RentalListDto response = modelMapperService.forDto().map(rental, RentalListDto.class);

		return new SuccessDataResult<RentalListDto>(response);
	}
	
	@Override
	public DataResult<Rental> getByCarId(Integer carId) {
		Rental rental = this.rentalDao.findByCarId(carId);
		return new SuccessDataResult<Rental>(rental);
	}
	
	@Override
	public DataResult<List<RentalListDto>> getAll() {
		List<Rental> rentalList = this.rentalDao.findAll();
		List<RentalListDto> response = rentalList.stream()
				.map(rental->modelMapperService.forDto()
						.map(rental, RentalListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<RentalListDto>>(response);
	}
	
	private Result checkCarExistRentalHistory(Integer rentDate, Integer returnDate) {
        
		if (rentDate<returnDate) {
			return new ErrorResult(Messages.illogicalRentDate);
		}

		return new SuccessResult();
	}
	
	private Result checkCarKilometer(Integer rentedKilometer, Integer returnedKilometer) {
        
		if (rentedKilometer<returnedKilometer) {
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

}
