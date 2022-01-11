package com.btk.academia.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btk.academia.rentACar.entities.concretes.Color;

public interface ColorDao extends JpaRepository<Color, Integer> {

}
