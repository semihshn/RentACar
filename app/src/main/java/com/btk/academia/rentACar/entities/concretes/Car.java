package com.btk.academia.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="daily_price")
	private Double dailyPrice;
	
	@Column(name="model_year")
	private Integer modelYear;
	
	@Column(name="description")
	private String description;
	
	@Column(name="findex_score")
	private Integer findexScore;
	
	@Column(name="kilometer")
	private Integer kilometer;

	@Column(name="min_age")
	private Integer minAge;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;
	
	@OneToMany(mappedBy="car")
	private List<CarMaintanance> carMaintanances;
	
	@OneToMany(mappedBy="car")
	private List<Rental> rentals;
	
	@OneToMany(mappedBy="car")
	private List<CarDamage> carDamages ;

}
