package com.prabu.serviceapi.technician;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.technician.model.TechnicianDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TechnicianService {

    List<TechnicianDTO> getTechnicianList();

    Page<TechnicianDTO> getTechnicianGrid(PaginationPage paginationPage);

    TechnicianDTO saveTechnician(TechnicianDTO technicianDTO);

    TechnicianDTO updateTechnician(Long id, TechnicianDTO technicianDTO);

    void deleteTechnician(Long id);
}
