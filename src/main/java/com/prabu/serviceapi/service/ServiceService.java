package com.prabu.serviceapi.service;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.service.model.ServiceDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServiceService {

    List<ServiceDTO> getServiceList();

    Page<ServiceDTO> getServiceGrid(PaginationPage paginationPage);

    ServiceDTO saveService(ServiceDTO serviceDTO);

    ServiceDTO updateService(Long id, ServiceDTO serviceDTO);

    void deleteService(Long id);
}
