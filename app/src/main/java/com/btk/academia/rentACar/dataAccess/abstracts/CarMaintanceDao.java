package com.btk.academia.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btk.academia.rentACar.entities.concretes.CarMaintanance;

public interface CarMaintanceDao extends JpaRepository<CarMaintanance, Integer> {
	
	CarMaintanance findByCarId(Integer carId);

}
