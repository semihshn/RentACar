package com.btk.academia.rentACar.core.adapters;

import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.entities.concretes.User;

public interface CustomerCheckLimitService {

    public Result checkIfLimitIsEnought();
}
