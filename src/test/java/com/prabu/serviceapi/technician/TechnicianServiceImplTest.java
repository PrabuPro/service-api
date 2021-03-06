package com.prabu.serviceapi.technician;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.technician.mapper.TechnicianMapper;
import com.prabu.serviceapi.technician.model.TechnicianDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TechnicianServiceImplTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "first name";
    public static final String LAST_NAME = "last name";
    @Mock
    TechnicianRepository technicianRepository;

    TechnicianService technicianService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        technicianService = new TechnicianServiceImpl(technicianRepository, TechnicianMapper.INSTANCE);
    }

    private List<Technician> getTechnicianArrayList(){
        Technician technician = new Technician();
        technician.setId(ID);
        technician.setFirstName(FIRST_NAME);
        technician.setLastName(LAST_NAME);

        Technician technician1 = new Technician();
        technician1.setId(ID);
        technician1.setFirstName(FIRST_NAME);
        technician1.setLastName(LAST_NAME);

        List<Technician> technicianList = Arrays.asList(technician,technician1);
        return technicianList;
    }

    @Test
    void getTechnicianList() {

        when(technicianRepository.findAll()).thenReturn(getTechnicianArrayList());

        List<TechnicianDTO> technicianDTOList = technicianService.getTechnicianList();

        assertEquals(getTechnicianArrayList().size(), technicianDTOList.size());

    }

    @Test
    void getTecnicianGrid(){
        Page<Technician> technicianPage = new PageImpl<>(getTechnicianArrayList());

        when(technicianRepository.findAll(any(Pageable.class))).thenReturn(technicianPage);

        PaginationPage paginationPage = new PaginationPage();
        paginationPage.setPageNumber(0);
        paginationPage.setPageSize(2);

        Page<TechnicianDTO> technicianDTOPage = technicianService.getTechnicianGrid(paginationPage);

        assertEquals(2,technicianDTOPage.getTotalElements());
    }

    @Test
    void saveTechnician(){
        Technician technician = new Technician();
        technician.setId(ID);
        technician.setFirstName(FIRST_NAME);
        technician.setLastName(LAST_NAME);

        TechnicianDTO technicianDTO = new TechnicianDTO();
        technicianDTO.setFirstName(FIRST_NAME);
        technicianDTO.setLastName(LAST_NAME);

        when(technicianRepository.save(any(Technician.class))).thenReturn(technician);

        TechnicianDTO saveTechnician = technicianService.saveTechnician(technicianDTO);

        assertEquals(saveTechnician.getId(), 1);
        assertEquals(saveTechnician.getFirstName(), FIRST_NAME);
    }

    @Test
    void updateTechnician(){
        Technician technician = new Technician();
        technician.setId(ID);
        technician.setFirstName(FIRST_NAME);
        technician.setLastName(LAST_NAME);

        TechnicianDTO technicianDTO = new TechnicianDTO();
        technicianDTO.setFirstName(FIRST_NAME);
        technicianDTO.setLastName(LAST_NAME);

        when(technicianRepository.save(any(Technician.class))).thenReturn(technician);

        TechnicianDTO saveTechnician = technicianService.updateTechnician(ID,technicianDTO);

        assertEquals(saveTechnician.getId(), 1);
        assertEquals(saveTechnician.getFirstName(), FIRST_NAME);
    }

    @Test
    void deleteTechnician(){
        technicianService.deleteTechnician(ID);

        verify(technicianRepository, times(1)).deleteById(anyLong());
    }
}