package com.prabu.serviceapi.vehicle;

import com.prabu.serviceapi.exception.GlobalExceptionHandler;
import com.prabu.serviceapi.vehicle.model.VehicleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VehicleControllerTest {

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
}