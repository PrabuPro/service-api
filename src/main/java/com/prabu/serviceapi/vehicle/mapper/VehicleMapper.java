package com.prabu.serviceapi.vehicle.mapper;

import com.prabu.serviceapi.vehicle.Vehicle;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);
    Vehicle vechicleDTOToVehicle(VehicleDTO vehicleDTO);
}
