package com.prabu.serviceapi.technician.mapper;

import com.prabu.serviceapi.technician.Technician;
import com.prabu.serviceapi.technician.model.TechnicianDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TechnicianMapper {

    TechnicianMapper INSTANCE = Mappers.getMapper(TechnicianMapper.class);

    Technician technicianDTOToTechnicin(TechnicianDTO technicianDTO);
    TechnicianDTO technicianToTechnicianDTO(Technician technician);

}
