package com.btk.academia.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btk.academia.rentACar.entities.concretes.AdditionalService;

import java.util.List;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {

    List<AdditionalService> findByRentalId(Integer rentalId);

}
