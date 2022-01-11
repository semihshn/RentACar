package com.btk.academia.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btk.academia.rentACar.entities.concretes.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer>{
	
	Brand findByName(String name);

}
