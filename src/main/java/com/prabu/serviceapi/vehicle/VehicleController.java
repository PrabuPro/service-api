package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.vehicle.model.VehicleListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VehicleController.API_V_1_VEHICLE)
public class VehicleController {

    public static final String API_V_1_VEHICLE = "/api/v1/vehicle";

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/list")
    public ResponseEntity<VehicleListDTO> getVehicleList(){
        return new ResponseEntity<>(
                new VehicleListDTO(vehicleService.getAllVehicles()), HttpStatus.OK);
    }
}
