package com.btk.academia.rentACar.dataAccess.abstracts;

import com.btk.academia.rentACar.entities.concretes.User;
import com.btk.academia.rentACar.entities.concretes.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
}
