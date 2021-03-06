package com.prabu.serviceapi.technician;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.technician.mapper.TechnicianMapper;
import com.prabu.serviceapi.technician.model.TechnicianDTO;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnicianServiceImpl implements TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final TechnicianMapper technicianMapper;

    public TechnicianServiceImpl(TechnicianRepository technicianRepository, TechnicianMapper technicianMapper) {
        this.technicianRepository = technicianRepository;
        this.technicianMapper = technicianMapper;
    }

    @Override
    public List<TechnicianDTO> getTechnicianList() {
        return technicianRepository.findAll()
                .stream()
                .map(technicianMapper::technicianToTechnicianDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TechnicianDTO> getTechnicianGrid(PaginationPage paginationPage) {
        Sort sort = Sort.by(paginationPage.getSortDirection(),paginationPage.getSortBy());
        Pageable pageable = PageRequest.of(
                paginationPage.getPageNumber(),
                paginationPage.getPageSize(),
                sort);

        List<TechnicianDTO> technicianDTOList = technicianRepository.findAll(pageable)
                .stream()
                .map(technicianMapper::technicianToTechnicianDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(technicianDTOList);
    }

    private TechnicianDTO saveTechnicianToRepository(Technician technician){
        return technicianMapper.technicianToTechnicianDTO(technicianRepository.save(technician));
    }

    @Override
    public TechnicianDTO saveTechnician(TechnicianDTO technicianDTO) {
        Technician technician = technicianMapper.technicianDTOToTechnicin(technicianDTO);

        return saveTechnicianToRepository(technician);
    }

    @Override
    public TechnicianDTO updateTechnician(Long id, TechnicianDTO technicianDTO) {
        Technician technician = technicianMapper.technicianDTOToTechnicin(technicianDTO);
        technician.setId(id);

        return saveTechnicianToRepository(technician);
    }

    @Override
    public void deleteTechnician(Long id) {
        technicianRepository.deleteById(id);
    }
}
