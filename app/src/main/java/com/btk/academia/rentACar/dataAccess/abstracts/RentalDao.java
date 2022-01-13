package com.btk.academia.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.Car;
import com.btk.academia.rentACar.entities.concretes.CarMaintanance;
import com.btk.academia.rentACar.entities.concretes.IndividualCustomer;
import com.btk.academia.rentACar.entities.concretes.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer> {
	
	Rental findByCarId(Integer carId);

}
