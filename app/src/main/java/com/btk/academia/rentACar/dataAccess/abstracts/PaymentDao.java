package com.btk.academia.rentACar.dataAccess.abstracts;

import com.btk.academia.rentACar.entities.concretes.IndividualCustomer;
import com.btk.academia.rentACar.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

}
