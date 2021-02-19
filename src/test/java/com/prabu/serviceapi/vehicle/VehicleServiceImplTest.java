package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.vehicle.mapper.VehicleMapper;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import com.prabu.serviceapi.vehicle.model.VehicleListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VehicleServiceImplTest {

    @Mock
    VehicleRepository vehicleRepository;

    VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleService = new VehicleServiceImpl(vehicleRepository, VehicleMapper.INSTANCE);
    }

    @Test
    void getAllVehicles() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleNumber("1234");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleNumber("1234");

        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);

        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<VehicleDTO> vehicleList = vehicleService.getAllVehicles();

        assertEquals(2, vehicleList.size());

    }
}