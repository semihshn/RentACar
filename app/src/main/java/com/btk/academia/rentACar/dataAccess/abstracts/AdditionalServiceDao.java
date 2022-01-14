package com.btk.academia.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btk.academia.rentACar.entities.concretes.AdditionalService;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {

    AdditionalService findByRentalId(Integer rentalId);

}
