package com.daniel.edupulse_api.service;

import com.daniel.edupulse_api.database.model.School;
import com.daniel.edupulse_api.database.repository.SchoolRepository;
import com.daniel.edupulse_api.dto.SchoolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<SchoolDTO> findAll(){
        return schoolRepository.findAll().stream()
                .map(s -> new SchoolDTO(s.getInepCode(), s.getName(), s.getCity(), s.getState(), s.isHasInternet(), s.getStudentCount()))
                .toList();
    }

    public SchoolDTO save(SchoolDTO dto){
        School school = School.builder()
                .inepCode(dto.inepCode())
                .name(dto.name())
                .city(dto.city())
                .state(dto.state())
                .hasInternet(dto.hasInternet())
                .studentCount(dto.studentCount())
                .build();

        School savedSchool = schoolRepository.save(school);

        return new SchoolDTO(
                savedSchool.getInepCode(),
                savedSchool.getName(),
                savedSchool.getCity(),
                savedSchool.getState(),
                savedSchool.isHasInternet(),
                savedSchool.getStudentCount()
        );
    }
}
