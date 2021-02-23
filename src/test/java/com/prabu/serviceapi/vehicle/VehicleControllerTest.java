package com.prabu.serviceapi.vehicle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prabu.serviceapi.customer.CustomerService;
import com.prabu.serviceapi.exception.GlobalExceptionHandler;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import com.prabu.serviceapi.vehicle.model.VehicleRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.prabu.serviceapi.customer.AbstractRestController.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VehicleControllerTest {

    public static final String VEHICLE_NUMBER = "123";
    public static final long ID = 1L;
    @Mock
    VehicleService vehicleService;


    @InjectMocks
    VehicleController vehicleController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
    }

    @Test
    void getVehicleList() throws Exception {
        VehicleDTO vehicleDTO1 = new VehicleDTO();
        vehicleDTO1.setVehicleNumber("123");

        VehicleDTO vehicleDTO2 = new VehicleDTO();
        vehicleDTO2.setVehicleNumber("123");

        List<VehicleDTO> vehicleDTOS = Arrays.asList(vehicleDTO1, vehicleDTO2);

        when(vehicleService.getAllVehicles()).thenReturn(vehicleDTOS);

        mockMvc.perform(get(VehicleController.API_V_1_VEHICLE + "/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicles", hasSize(2)));
    }

    @Test
    void saveVehicle() throws Exception {
        VehicleRequestDTO vehicleDTO1 = new VehicleRequestDTO();
        vehicleDTO1.setVehicleNumber(VEHICLE_NUMBER);
        vehicleDTO1.setCustomer(1L);

        VehicleDTO vehicleReturn = new VehicleDTO();
        vehicleReturn.setId(ID);
        vehicleReturn.setVehicleNumber(VEHICLE_NUMBER);

        when(vehicleService.saveVehicle(vehicleDTO1)).thenReturn(vehicleReturn);

        mockMvc.perform(post(VehicleController.API_V_1_VEHICLE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vehicleDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleNumber", equalTo(VEHICLE_NUMBER)));
    }

    @Test
    void updateVehicle() throws Exception {
        VehicleRequestDTO vehicleDTO1 = new VehicleRequestDTO();
        vehicleDTO1.setVehicleNumber(VEHICLE_NUMBER);
        vehicleDTO1.setCustomer(1L);

        VehicleDTO vehicleReturn = new VehicleDTO();
        vehicleReturn.setId(ID);
        vehicleReturn.setVehicleNumber(VEHICLE_NUMBER);

        when(vehicleService.updateVehicle(ID,vehicleDTO1)).thenReturn(vehicleReturn);

        mockMvc.perform(put(VehicleController.API_V_1_VEHICLE + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vehicleDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleNumber", equalTo(VEHICLE_NUMBER)));
    }
}