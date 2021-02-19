package com.prabu.serviceapi.vehicle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class VehicleListDTO {
    private List<VehicleDTO> vehicles;
}
