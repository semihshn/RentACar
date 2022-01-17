package com.btk.academia.rentACar.dataAccess.abstracts;

import com.btk.academia.rentACar.entities.concretes.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeDao extends JpaRepository<PromoCode, Integer> {
    PromoCode findByPromationCode(String promotionCode);
}
