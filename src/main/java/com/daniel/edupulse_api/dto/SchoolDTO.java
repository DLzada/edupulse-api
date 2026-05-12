package com.daniel.edupulse_api.dto;

import com.daniel.edupulse_api.domain.model.SchoolLevel;

public record SchoolDTO(
        String inepCode,
        String name,
        String city,
        String state,
        Integer studentCount,
        SchoolLevel level,
        boolean hasInternet,
        boolean hasLibrary,
        boolean hasComputerLab,
        boolean hasSportsCourt,
        Double infrastructureScore
) {}
