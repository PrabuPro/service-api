package com.prabu.serviceapi.technician;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prabu.serviceapi.exception.GlobalExceptionHandler;
import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.technician.model.TechnicianDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.prabu.serviceapi.technician.TechnicianServiceImplTest.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class TechnicianControllerTest {


    @Mock
    TechnicianService technicianService;

    @InjectMocks
    TechnicianController technicianController;

    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(technicianController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    private List<TechnicianDTO> getTechnicianArrayList(){
        TechnicianDTO technician = new TechnicianDTO();
        technician.setId(ID);
        technician.setFirstName(FIRST_NAME);
        technician.setLastName(LAST_NAME);

        TechnicianDTO technician1 = new TechnicianDTO();
        technician1.setId(ID);
        technician1.setFirstName(FIRST_NAME);
        technician1.setLastName(LAST_NAME);

        List<TechnicianDTO> technicianList = Arrays.asList(technician,technician1);

        return technicianList;
    }

    @Test
    void getTechnicianList() throws Exception {

        when(technicianService.getTechnicianList()).thenReturn(getTechnicianArrayList());

        mockMvc.perform(get(TechnicianController.API_V_1_TECHNICIAN + "/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.technicians", hasSize(2)));
    }

    @Test
    void getTechnicalGrid() throws Exception {
        Page<TechnicianDTO> technicianDTOPage = new PageImpl<>(getTechnicianArrayList());

        when(technicianService.getTechnicianGrid(ArgumentMatchers.any(PaginationPage.class)))
                .thenReturn(technicianDTOPage);

        mockMvc.perform(get(TechnicianController.API_V_1_TECHNICIAN + "/grid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveTechnician() throws Exception {
        TechnicianDTO technician = new TechnicianDTO();
        technician.setId(ID);
        technician.setFirstName(FIRST_NAME);
        technician.setLastName(LAST_NAME);

        TechnicianDTO technicianRequest = new TechnicianDTO();
        technicianRequest.setFirstName(FIRST_NAME);
        technicianRequest.setLastName(LAST_NAME);

        when(technicianService.saveTechnician(ArgumentMatchers.any(TechnicianDTO.class))).thenReturn(technician);

        mockMvc.perform(post(TechnicianController.API_V_1_TECHNICIAN)
                .content(objectMapper.writeValueAsString(technicianRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

    }

    @Test
    void updateTechnician() throws Exception {
        TechnicianDTO technician = new TechnicianDTO();
        technician.setId(ID);
        technician.setFirstName(FIRST_NAME);
        technician.setLastName(LAST_NAME);

        TechnicianDTO technicianRequest = new TechnicianDTO();
        technicianRequest.setFirstName(FIRST_NAME);
        technicianRequest.setLastName(LAST_NAME);

        when(technicianService.updateTechnician(anyLong(),ArgumentMatchers.any(TechnicianDTO.class)))
                .thenReturn(technician);

        mockMvc.perform(put(TechnicianController.API_V_1_TECHNICIAN + "/1")
                .content(objectMapper.writeValueAsString(technicianRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));

    }

    @Test
    void deleteTechnician() throws Exception {
        mockMvc.perform(delete(TechnicianController.API_V_1_TECHNICIAN + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(technicianService, times(1)).deleteTechnician(anyLong());
    }
}