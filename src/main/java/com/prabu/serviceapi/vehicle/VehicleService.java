package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import com.prabu.serviceapi.vehicle.model.VehicleListDTO;
import com.prabu.serviceapi.vehicle.model.VehicleRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> getAllVehicles();

    Page<VehicleDTO> getVehicleGrid(PaginationPage paginationPage);

    VehicleDTO saveVehicle(VehicleRequestDTO vehicleDTO);

    VehicleDTO updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO);

    void deleteVehicle(Long id);
}
