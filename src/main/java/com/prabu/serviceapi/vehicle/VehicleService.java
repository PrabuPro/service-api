package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import com.prabu.serviceapi.vehicle.model.VehicleListDTO;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> getAllVehicles();

}
