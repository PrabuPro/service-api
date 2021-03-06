package com.prabu.serviceapi.service;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.service.mapper.ServiceMapper;
import com.prabu.serviceapi.service.model.ServiceDTO;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public List<ServiceDTO> getServiceList() {
        return serviceRepository.findAll()
                .stream()
                .map(serviceMapper::serviceToServiceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ServiceDTO> getServiceGrid(PaginationPage paginationPage) {
        Sort sort = Sort.by(paginationPage.getSortDirection(), paginationPage.getSortBy());
        Pageable pageable = PageRequest.of(
                paginationPage.getPageNumber(),
                paginationPage.getPageSize(),
                sort);

        List<ServiceDTO> serviceList = serviceRepository.findAll(pageable)
                .stream()
                .map(serviceMapper::serviceToServiceDTO)
                .collect(Collectors.toList());


        return new PageImpl<>(serviceList);
    }

    @Override
    public ServiceDTO saveService(ServiceDTO serviceDTO) {
        Service service = serviceMapper.serviceDTOToService(serviceDTO);

        return serviceSaveToRepository(service);
    }

    @Override
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {
        Service service = serviceMapper.serviceDTOToService(serviceDTO);
        service.setId(id);

        return serviceSaveToRepository(service);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    private ServiceDTO serviceSaveToRepository(Service service){
        return serviceMapper.serviceToServiceDTO(serviceRepository.save(service));
    }
}
