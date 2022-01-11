package com.btk.academia.rentACar.core.utilities.business;

import com.btk.academia.rentACar.core.utilities.results.Result;
import com.btk.academia.rentACar.core.utilities.results.SuccessResult;

public class BusinessRules {
	public static Result run(Result... logics) {
		for (Result logic : logics)
			if (!logic.isSuccess())
				return logic;
		return new SuccessResult();
	}
}
