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
}
