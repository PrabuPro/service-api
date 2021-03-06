package com.prabu.serviceapi.service;

import com.prabu.serviceapi.exception.GlobalExceptionHandler;
import com.prabu.serviceapi.service.model.ServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.prabu.serviceapi.customer.AbstractRestController.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ServiceControllerTest {

    public static final long ID = 1L;
    public static final String SERVICE_NAME = "Service name";
    @Mock
    ServiceService serviceService;

    @InjectMocks
    ServiceController serviceController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(serviceController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getServiceList() throws Exception {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(ID);
        serviceDTO.setServiceName(SERVICE_NAME);
        serviceDTO.setServiceCharge(BigDecimal.valueOf(100.00));

        ServiceDTO serviceDTO1 = new ServiceDTO();
        serviceDTO1.setId(ID);
        serviceDTO1.setServiceName(SERVICE_NAME);
        serviceDTO1.setServiceCharge(BigDecimal.valueOf(100.00));

        List<ServiceDTO> serviceDTOList = Arrays.asList(serviceDTO, serviceDTO1);

        when(serviceService.getServiceList()).thenReturn(serviceDTOList);

        mockMvc.perform(get(ServiceController.API_V_1_SERVICE + "/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.services", hasSize(2)));

    }

    @Test
    void saveService() throws Exception {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(ID);
        serviceDTO.setServiceName(SERVICE_NAME);
        serviceDTO.setServiceCharge(BigDecimal.valueOf(100.00));

        ServiceDTO serviceRequest = new ServiceDTO();
        serviceRequest.setServiceName(SERVICE_NAME);
        serviceRequest.setServiceCharge(BigDecimal.valueOf(100.00));

        when(serviceService.saveService(ArgumentMatchers.any(ServiceDTO.class))).thenReturn(serviceDTO);

        mockMvc.perform(post(ServiceController.API_V_1_SERVICE)
                .content(asJsonString(serviceRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    void udpateService() throws Exception {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(ID);
        serviceDTO.setServiceName(SERVICE_NAME);
        serviceDTO.setServiceCharge(BigDecimal.valueOf(100.00));

        ServiceDTO serviceRequest = new ServiceDTO();
        serviceRequest.setServiceName(SERVICE_NAME);
        serviceRequest.setServiceCharge(BigDecimal.valueOf(100.00));

        when(serviceService.updateService(anyLong(),ArgumentMatchers.any(ServiceDTO.class))).thenReturn(serviceDTO);

        mockMvc.perform(put(ServiceController.API_V_1_SERVICE + "/1")
                .content(asJsonString(serviceRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.serviceName", equalTo(SERVICE_NAME)));
    }

    @Test
    void deleteService() throws Exception {
        mockMvc.perform(delete(ServiceController.API_V_1_SERVICE + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}