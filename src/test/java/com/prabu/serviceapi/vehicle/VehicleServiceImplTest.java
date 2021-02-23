package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.customer.Customer;
import com.prabu.serviceapi.customer.CustomerRepository;
import com.prabu.serviceapi.vehicle.mapper.VehicleMapper;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import com.prabu.serviceapi.vehicle.model.VehicleRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class VehicleServiceImplTest {

    public static final String VEHICLE_NUMBER = "1234";
    public static final long ID = 1L;
    @Mock
    VehicleRepository vehicleRepository;

    @Mock
    CustomerRepository customerRepository;

    VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vehicleService = new VehicleServiceImpl(vehicleRepository, customerRepository, VehicleMapper.INSTANCE);
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

    @Test
    void saveVehicle(){
        VehicleRequestDTO vehicleDTO = new VehicleRequestDTO();
        vehicleDTO.setVehicleNumber(VEHICLE_NUMBER);
        vehicleDTO.setCustomer(1L);

        Customer customer = new Customer();
        customer.setId(1L);

        Vehicle vehicleReturn = new Vehicle();
        vehicleReturn.setId(ID);
        vehicleReturn.setVehicleNumber(VEHICLE_NUMBER);

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicleReturn);
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        VehicleDTO saveVehicle = vehicleService.saveVehicle(vehicleDTO);

        assertEquals(saveVehicle.getId(), ID);
        assertEquals(saveVehicle.getVehicleNumber(), VEHICLE_NUMBER);
    }

    @Test
    void updateVehicle(){
        VehicleRequestDTO vehicleDTO = new VehicleRequestDTO();
        vehicleDTO.setVehicleNumber(VEHICLE_NUMBER);
        vehicleDTO.setCustomer(1L);

        Customer customer = new Customer();
        customer.setId(1L);

        Vehicle vehicleReturn = new Vehicle();
        vehicleReturn.setId(ID);
        vehicleReturn.setVehicleNumber(VEHICLE_NUMBER);

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicleReturn);
        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        VehicleDTO saveVehicle = vehicleService.updateVehicle(ID,vehicleDTO);

        assertEquals(saveVehicle.getId(), ID);
        assertEquals(saveVehicle.getVehicleNumber(), VEHICLE_NUMBER);
    }
}