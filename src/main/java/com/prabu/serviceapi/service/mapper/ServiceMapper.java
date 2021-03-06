package com.prabu.serviceapi.service.mapper;

import com.prabu.serviceapi.service.Service;
import com.prabu.serviceapi.service.model.ServiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ServiceMapper {

    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    Service serviceDTOToService(ServiceDTO serviceDTO);
    ServiceDTO serviceToServiceDTO(Service service);
}
