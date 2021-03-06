package com.prabu.serviceapi.service;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.service.mapper.ServiceMapper;
import com.prabu.serviceapi.service.model.ServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ServiceServiceImplTest {

    public static final long ID = 1L;
    public static final String SERVICE_NAME = "Service name";
    @Mock
    ServiceRepository serviceRepository;

    ServiceService serviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceService = new ServiceServiceImpl(serviceRepository, ServiceMapper.INSTANCE);
    }

    private List<Service> getServiceArrayList(){
        Service service = new Service();
        service.setId(ID);
        service.setServiceName(SERVICE_NAME);
        service.setServiceCharge(BigDecimal.valueOf(100.10));

        Service service1 = new Service();
        service1.setId(ID);
        service1.setServiceName(SERVICE_NAME);
        service1.setServiceCharge(BigDecimal.valueOf(100.10));

        List<Service> serviceList = Arrays.asList(service, service1);

        return serviceList;
    }

    @Test
    void getServiceList() {

        List<Service> serviceList = getServiceArrayList();

        when(serviceRepository.findAll()).thenReturn(serviceList);

        List<ServiceDTO> serviceDTOList = serviceService.getServiceList();

        assertEquals(2, serviceDTOList.size());
    }

    @Test
    void getServiceGrid(){
        List<Service> serviceList = getServiceArrayList();

        Page<Service> serviceListPage = new PageImpl<>(serviceList);

        when(serviceRepository.findAll(any(Pageable.class))).thenReturn(serviceListPage);

        PaginationPage paginationPage = new PaginationPage();
        paginationPage.setPageNumber(0);
        paginationPage.setPageSize(2);

        Page<ServiceDTO> serviceDTOPage = serviceService.getServiceGrid(paginationPage);

        assertEquals(2, serviceDTOPage.getTotalElements());
    }

    @Test
    void saveService(){
        Service service = new Service();
        service.setId(ID);
        service.setServiceName(SERVICE_NAME);
        service.setServiceCharge(BigDecimal.valueOf(100.10));

        ServiceDTO serviceDTO  = new ServiceDTO();
        serviceDTO.setServiceName(SERVICE_NAME);
        serviceDTO.setServiceCharge(BigDecimal.valueOf(100.10));

        when(serviceRepository.save(any(Service.class))).thenReturn(service);

        ServiceDTO serviceSave = serviceService.saveService(serviceDTO);

        assertEquals(serviceSave.getId(), ID);
        assertEquals(serviceSave.getServiceName(), SERVICE_NAME);
    }

    @Test
    void updateService(){
        Service service = new Service();
        service.setId(ID);
        service.setServiceName(SERVICE_NAME);
        service.setServiceCharge(BigDecimal.valueOf(100.10));

        ServiceDTO serviceDTO  = new ServiceDTO();
        serviceDTO.setServiceName(SERVICE_NAME);
        serviceDTO.setServiceCharge(BigDecimal.valueOf(100.10));

        when(serviceRepository.save(any(Service.class))).thenReturn(service);

        ServiceDTO serviceSave = serviceService.updateService(ID, serviceDTO);

        assertEquals(serviceSave.getId(), ID);
        assertEquals(serviceSave.getServiceName(), SERVICE_NAME);
    }

    @Test
    void deleteService(){
        serviceService.deleteService(1L);
        verify(serviceRepository, times(1)).deleteById(anyLong());
    }
}