package com.btk.academia.rentACar.business.abstracts;

import java.util.List;

import com.btk.academia.rentACar.business.dtos.ColorDto;
import com.btk.academia.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btk.academia.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btk.academia.rentACar.core.utilities.results.DataResult;
import com.btk.academia.rentACar.core.utilities.results.Result;

public interface ColorService {
	
	DataResult<List<ColorDto>> getAll();
	Result add(CreateColorRequest createColorRequest);
	Result update(UpdateColorRequest updateColorRequest);

}
