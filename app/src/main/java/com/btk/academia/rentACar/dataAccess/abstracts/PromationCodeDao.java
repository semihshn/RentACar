package com.btk.academia.rentACar.dataAccess.abstracts;

import com.btk.academia.rentACar.entities.concretes.PromationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromationCodeDao extends JpaRepository<PromationCode, Integer> {
    PromationCode findByPromationCode(String promotionCode);
}
