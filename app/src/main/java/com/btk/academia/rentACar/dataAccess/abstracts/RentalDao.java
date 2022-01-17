package com.btk.academia.rentACar.dataAccess.abstracts;

import com.btk.academia.rentACar.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import com.btk.academia.rentACar.entities.concretes.Rental;

import java.util.List;

public interface RentalDao extends JpaRepository<Rental, Integer> {
	
	Rental findByCarId(Integer carId);
	List<Rental> findByCustomerId(Integer customerId);

}
