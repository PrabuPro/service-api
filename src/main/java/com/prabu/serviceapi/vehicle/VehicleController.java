package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import com.prabu.serviceapi.vehicle.model.VehicleListDTO;
import com.prabu.serviceapi.vehicle.model.VehicleRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/grid")
    public ResponseEntity<Page<VehicleDTO>> getVehicleGrid(PaginationPage paginationPage){
        return new ResponseEntity<>(vehicleService.getVehicleGrid(paginationPage), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleRequestDTO vehicleDTO){
        return new ResponseEntity<VehicleDTO>(vehicleService.saveVehicle(vehicleDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequestDTO vehicleRequestDTO){
        return new ResponseEntity<VehicleDTO>(vehicleService.updateVehicle(id, vehicleRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/findByCustomer/{id}")
    public ResponseEntity<VehicleListDTO> findByCustomer(@PathVariable Long id){
        return new ResponseEntity<>(new VehicleListDTO(vehicleService.findAllByCustomer(id)), HttpStatus.OK);
    }
}
