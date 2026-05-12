package com.daniel.edupulse_api.service;

import com.daniel.edupulse_api.domain.model.School;
import com.daniel.edupulse_api.domain.repository.SchoolRepository;
import com.daniel.edupulse_api.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<SchoolDTO> findAll(){
        return schoolRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    private SchoolDTO mapToDTO(School school) {
        double score = 0.0;

        if(school.isHasInternet()) score += 30.0;
        if (school.isHasLibrary()) score += 30.0;
        if (school.isHasComputerLab()) score += 20.0;
        if (school.isHasSportsCourt()) score += 20.0;

        return new SchoolDTO(
                school.getInepCode(),
                school.getName(),
                school.getCity(),
                school.getState(),
                school.getStudentCount(),
                school.getLevel(),
                school.isHasInternet(),
                school.isHasLibrary(),
                school.isHasComputerLab(),
                school.isHasSportsCourt(),
                score
        );
    }

    public SchoolDTO save(SchoolDTO dto){
        School school = School.builder()
                .inepCode(dto.inepCode())
                .name(dto.name())
                .city(dto.city())
                .state(dto.state())
                .studentCount(dto.studentCount())
                .level(dto.level())
                .hasInternet(dto.hasInternet())
                .hasLibrary(dto.hasLibrary())
                .hasComputerLab(dto.hasComputerLab())
                .hasSportsCourt(dto.hasSportsCourt())
                .build();

        School savedSchool = schoolRepository.save(school);

        return mapToDTO(savedSchool);
    }

    public List<SchoolDTO> getRankingByCity(String city){
        return schoolRepository.findByCityIgnoreCase(city).stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(SchoolDTO::infrastructureScore).reversed())
                .toList();
    }
}
