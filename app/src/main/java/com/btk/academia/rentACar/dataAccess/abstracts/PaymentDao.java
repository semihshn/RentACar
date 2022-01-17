package com.btk.academia.rentACar.dataAccess.abstracts;

import com.btk.academia.rentACar.entities.concretes.IndividualCustomer;
import com.btk.academia.rentACar.entities.concretes.Payment;
import com.btk.academia.rentACar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Integer> {
    List<Payment> findByRentalId(Integer rentalId);
}
