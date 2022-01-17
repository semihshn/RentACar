package com.btk.academia.rentACar.business.concretes;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.btk.academia.rentACar.business.abstracts.*;
import com.btk.academia.rentACar.business.dtos.CarDto;
import com.btk.academia.rentACar.business.dtos.CustomerDto;
import com.btk.academia.rentACar.business.dtos.IndividualCustomerDto;
import com.btk.academia.rentACar.entities.concretes.Car;
import com.btk.academia.rentACar.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	private IndividualCustomerService individualCustomerService;

	@Autowired
	public RentalManager(RentalDao rentalDao,
						 ModelMapperService modelMapperService,
						 CustomerService customerService,
						 @Lazy CarMaintanceService carMaintanceService,
						 CarService carService,
						 IndividualCustomerService individualCustomerService) {
		this.rentalDao=rentalDao;
		this.modelMapperService=modelMapperService;
		this.customerService=customerService;
		this.carMaintanceService=carMaintanceService;
		this.carService=carService;
		this.individualCustomerService=individualCustomerService;
	}

	@Override
	public Result addForCorporate(CreateRentalRequest createRentalRequest) {
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
	public Result addForIndividual(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(
				checkCarExistRentalHistory(createRentalRequest.getRentDate().getYear(),createRentalRequest.getReturnDate().getYear()),
				checkCarKilometer(createRentalRequest.getRentedKilometer(),createRentalRequest.getReturnedKilometer()),
				checkIfCustomerExists(createRentalRequest.getCustomerId()),
				checkCarMaintanance(createRentalRequest.getCarId()),
				checkIndividualCustomerAge(createRentalRequest.getCarId(),createRentalRequest.getCustomerId())
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
	public DataResult<List<RentalDto>> getByCustomerId(Integer customerId) {
		List<Rental> rentalList = this.rentalDao.findByCustomerId(customerId);

		List<RentalDto> response = rentalList.stream()
				.map(rental->modelMapperService.forDto()
						.map(rental, RentalDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<RentalDto>>(response);
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

	private Result checkIndividualCustomerAge(Integer carId, Integer customerId) {
		DataResult<CarDto> car=carService.getById(carId);
		DataResult<IndividualCustomerDto> customer=individualCustomerService.getById(customerId);

		Integer customerAge= LocalDateTime.now().getYear()-customer.getData().getBirthDate().getYear();

		if(car.getData().getMinAge()>customerAge){
			return new ErrorResult("Arabayı kiralayabilmek için yaşınız uygun değil");
		}

		return new SuccessResult();
	}
	
	private Result checkIfCustomerExists(Integer customerId) {
        
		if (customerService.getById(customerId)==null) {
			return new ErrorResult(Messages.notAvailableCustomer);
		}

		return new SuccessResult();
	}

	private Result checkIfCustomerFindeksScore(Integer carId,Integer customerId) {

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
