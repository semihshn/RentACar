package com.btk.academia.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customers")
public class Customer extends User{

	@Column(name="findeks_score")
	private Integer findeksScore;
	
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Rental> rentals;

	@OneToMany(mappedBy="customer")
	private List<UserInfo> userInfo;

}
