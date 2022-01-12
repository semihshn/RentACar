package com.btk.academia.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customers")
public class Customer extends User{
	
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Rental> rentals;

}
