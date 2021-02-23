package com.prabu.serviceapi.vehicle.mapper;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.vehicle.Vehicle;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import com.prabu.serviceapi.vehicle.model.VehicleRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

//    @Mapping(target = "customerDTO", source = "customer")
    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);
//    Vehicle vechicleDTOToVehicle(VehicleDTO vehicleDTO);

    @Mapping(target = "id", source = "vehicleRequestDTO.id")
    @Mapping(target = "customer", source = "customer")
    Vehicle vehicleRequestToVehicle(VehicleRequestDTO vehicleRequestDTO, Customer customer);

}
