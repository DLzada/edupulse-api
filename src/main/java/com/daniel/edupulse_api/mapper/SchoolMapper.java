package com.daniel.edupulse_api.mapper;

import com.daniel.edupulse_api.database.model.School;
import com.daniel.edupulse_api.dto.SchoolDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
    SchoolMapper INSTANCE = Mappers.getMapper(SchoolMapper.class);

    SchoolDTO toDTO(School school);

    School toEntity(SchoolDTO schoolDTO);
}
